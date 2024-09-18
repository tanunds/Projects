from flask import Flask, render_template, request, jsonify
import os
from tensorflow import keras
import numpy as np
from PIL import Image

app = Flask(__name__)

# Load the pre-trained model
current_file_directory = os.path.dirname(os.path.abspath(__file__))
model_path = os.path.join(current_file_directory, "models/model.h5")
model = keras.models.load_model(model_path)

@app.route("/", methods=["GET", "POST"])
def index():
    
    prediction_result = None

    if request.method == "POST" and "photo" in request.files:
        photo = request.files["photo"]

        if photo:
            # Save the uploaded image
            photo_path = os.path.join(current_file_directory, "uploads", photo.filename)
            photo.save(photo_path)

            # Perform image classification
            prediction_result = classify_image(photo_path)

            # Return the result as JSON
            # return jsonify({"result": result})

    return render_template("index.html", prediction_result=prediction_result)

def classify_image(image_path, threshold=0.000877):
    # Preprocess the image for the model
    with Image.open(image_path) as im:
        # Convert the image to a NumPy array
        image = np.array(im)
    # image = image.resize((40, 24))  # Adjust the size based on your model requirements
    image = np.expand_dims(image, axis=-1)
    image = np.expand_dims(image, axis=0)
    image = image / 255  # Normalize the pixel values

    # # Make a prediction using the pre-trained model
    recon = model.predict(image)

    mse = np.mean((image - recon) ** 2)

    if mse > threshold:
        result = f"Anomaly: mse = {mse}"
    else:
        result = f"No-Anomaly: mse = {mse}"

    return result

if __name__ == "__main__":
    app.run(debug=True,host="0.0.0.0", port=8080)
