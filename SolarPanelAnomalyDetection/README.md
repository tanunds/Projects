# SolarPanelAnomalyDetection

Detect solar panel anomaly from infrared image of solar panel. 

## Project structure

```
├───autoencoder
├───InfraredSolarModules
│   └───images
└───solar_anomaly_detection_app
    ├───models
    ├───templates
    ├───uploads
    └───app.py
```

1. [autoencoder](#autoencoder)
2. [InfraredSolarModules](#infraredsolarmodules)
3. [solar_anomaly_detection_app](#solar_anomaly_detection_app)

### autoencoder

Python notebook for data exploration, pre-processing, and training autoencoder model.

### InfraredSolarModules

Dataset for training.

### solar_anomaly_detection_app

Web application that can be run on server.
- models - pre-trained model from Python notebook
- templates - HTML template for user interface
- uploads - store uploaded image for inference
- app.py - main code running on server

## References
- https://www.kaggle.com/datasets/marcosgabriel/infrared-solar-modules
- https://pyimagesearch.com/2020/03/02/anomaly-detection-with-keras-tensorflow-and-deep-learning/
