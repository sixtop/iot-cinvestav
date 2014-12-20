#!/usr/bin/env python

from collections import OrderedDict

DATABASE_NAMES = OrderedDict([("main_system", "Main System"),
                            ("germinator", "Germinator"),
                            ("aero_1", "Mini-Aero System")])
COLLECTION_NAMES = {
    "main_system": OrderedDict([("water_sensors", "Water Sensors"),
        ("water_sensors_2", "Water Sensors 2"),
        ("water_sensors_3", "Water Sensors 3"),
        ("air_sensors", "Air Sensors"),
        ("air_sensors_3", "Air Sensors 3")]),
    "germinator": OrderedDict([("water_sensors", "Water Sensors"),
        ("air_sensors", "Air Sensors")]),
    "aero_1": OrderedDict([("water_sensors", "Water Sensors"),
        ("air_sensors", "Air Sensors")])
}
__temperature_info = ("Temperature", {
    "range": [15, 30],
    "ticks": [15, 20, 25, 30],
    "green": [20, 25],
    "yellow": [18, 27]
})
__ph_info = ("PH", {
    "range": [5, 7],
    "ticks": [5, 5.5, 6, 6.5, 7],
    "green": [5.7, 6.3],
    "yellow": [5.5, 6.5]
})
__do_info = ("DO", {
    "range": [0, 20],
    "ticks": ["0", 5, 10, 15, 20],
    "green": [7, 13],
    "yellow": [5, 15]
})
__orp_info = ("ORP", {
    "range": [-500, 500],
    "ticks": [-500, -250, "0", 250, 500],
    "green": [250, 400],
    "yellow": [200, 450]
})
__ec_info = ("EC", {
    "range": [0, 2000],
    "ticks": ["0", 500, 1000, 1500, 2000],
    "green": [700, 1300],
    "yellow": [500, 1500]
})
__sal_info = ("SAL", {
    "range": [0, 3],
    "ticks": ["0",1, 2, 3],
    "green": [0.2, 2],
    "yellow": [0.1, 2.1]
})
__tds_info = ("TDS", {
    "range": [0, 2000],
    "ticks": ["0", 500, 1000, 1500, 2000],
    "green": [500, 1000],
    "yellow": [0, 1500]
})
__sg_info = ("SG", {
    "range": [0.6, 1.4],
    "ticks": [0.6, 0.8, 1, 1.2, 1.4],
    "green": [0.8, 1.2],
    "yellow": [0.7, 1.3]
})
_water_sensors_info = OrderedDict([
    __temperature_info,
    __ph_info,
    __do_info,
    __orp_info,
    __ec_info,
    __sal_info,
    __tds_info,
    __sg_info
])
__humidity_info = ("Humidity", {
    "range": [0, 100],
    "ticks": ["0", 25, 50, 75, 100],
    "green": [40, 60],
    "yellow": [30, 70]
})
__CO2_info = ("CO2", {
    "range": [0, 1000],
    "ticks": ["0", 250, 500, 750, 1000],
    "green": [400, 600],
    "yellow": [300, 700]
})
__CO_info = ("CO", {
    "range": [0, 1000],
    "ticks": ["0", 250, 500, 750, 1000],
    "green": [400, 600],
    "yellow": [300, 700]
})
_air_sensors_info = OrderedDict([
    __temperature_info,
    __humidity_info,
    __CO2_info
])

COLLECTION_INFO = {
    "main_system": {
        "water_sensors": _water_sensors_info,
        "water_sensors_2": _water_sensors_info,
        "water_sensors_3": _water_sensors_info,
        "air_sensors": _air_sensors_info,
        "air_sensors_3": _air_sensors_info
    },
    "germinator": {
	"water_sensors": _water_sensors_info,
        "air_sensors": _air_sensors_info
    },
    "aero_1": {
	"water_sensors": _water_sensors_info,
        "air_sensors": _air_sensors_info
    }
}
