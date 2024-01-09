# Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices
_일상의 무선 장치 IoT화를 위한 블루투스 스피커 제어 시스템(App)_

<p align="right">
  <a href="https://blog.naver.com/pixelwizard/223317548521">
    <img src="https://img.shields.io/badge/한국어%20번역본-03C75A?style=flat-square&logo=Naver&logoColor=white" alt="네이버 블로그">
  </a> </p>  
  
![화면4](https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/29a1e1cc-0f22-453b-97dd-d65fbf48d09f)

## Project Genesis (프로젝트 발단)

The idea originated while conceptualizing a domestic IoT smart home integration project. It was noticed that people commonly use wireless devices without recognizing their unique specifications (API, MAC, etc.). This led to concerns that in the future smart home era, current wireless products unable to remotely interconnect due to lack of technological advancement might become obsolete. To prevent such significant waste of societal resources, this research project was initiated.

**※ Development Period : 2024.01.09 (9.5h)**

<br> <br> <br>

## Project Overview (프로젝트 개요)

This project aims to integrate Bluetooth-based wireless products, which lack specific information, into the IoT to become part of the smart home system. This **diverges from the traditional approach of using API, SDK, MAC addresses for IoT wireless integration.** The project explores how to maximize the wireless capabilities of each device and make IoT accessible to everyone through an app. It focuses on implementing numerous ideas in code using Kotlin for Android app development, primarily for research purposes rather than practical app development.

<br> <br> <br>

## Project Target Setting (프로젝트 대상 설정)

The focus is on an **unidentified Bluetooth-based wireless speaker** purchased from a Vietnamese night market. It contains basic information like a 3.7V 1200mAh battery and Bluetooth pairing capability, but **lacks crucial details like API and company information**, making it an ideal research subject.

<br> <br> <br>

## Project Direction (프로젝트 목표 방향 )

- **The aim is** to find common household wireless products and **devise various solutions for their IoT integration.** 
- **The goal is** to innovate user experience by integrating everyday Bluetooth devices into the IoT environment,  
                simplifying user interfaces, and **applying IoT technology in daily life.**

<br> <br> <br>

## Research Approach (프로젝트 연구 방향)

Smartphones can easily connect to wireless products through Bluetooth with a single button press. However, devices like Bluetooth speakers **require manual interaction for connection**, especially for new or shared products. The project **aims to develop a mechanism where wireless products can be turned on or off upon user request through an app, and the app automatically registers and connects to the device.**

<br> <br> <br>

## Technical Stack and Libraries (기술 스택 및 라이브러리)

- **Development Environment:** Android Studio, Kotlin
- **UI Design/Planning:** XML-based user interface with simple ON/OFF switch
- **Bluetooth Connection and Management:** Android Bluetooth API
- **Firmware Management:** FOTA (Firmware Over The Air) for remote updates
- **Smart Plug Control:** Using HTTP protocol-based RESTful API

<br> <br> <br>

## Bluetooth Connection and Control Logic (블루투스 연결 및 제어 로직)

- **Initial Setup:** The phone's Bluetooth automatically activates upon app launch.
- **Connection Process:** Sequentially implementing Bluetooth search and connection logic in MainActivity.kt.
- **Firmware Auto-Check:** Confirming low power mode operation of the speaker and updating firmware if necessary.
- **Smart Plug Integration:** Indirect power device control through smart plugs if direct control is unfeasible.

<br> <br> <br>

## Project Attempts (프로젝트 시도 사항)

- **Step 1 :** Develop a system where the app finds and connects to a low-power Bumblebee Bluetooth speaker.
- **Step 2 :** Automatically check for low-power ON/OFF capability in firmware.
- **Step 3 :** If unavailable, remotely upgrade the firmware to control the speaker's power.
- **Step 4 :** Automate steps 1-3 in the app, and manage power through Bluetooth location and permission requests or smart plugs.
- **Step 5 :** Change the app interface and device power status with a button press.
- **Step 6 :** Turn off the external power and switch to low-power mode (if the firmware supports it).

<br> <br> <br>

## Challenges and solutions (문제점 및 해결 방법)

- **Technical Limitation:** Currently, remote control of wireless devices without power (Bluetooth off) is not feasible without advanced design for constant low-power state maintenance, similar to TV remotes.
- **Alternative Approaches:** Indirect control using smart plugs, custom commands, FOTA for firmware updates, or equivalent methods (port forwarding, remote access settings).

<br> <br> <br>

## Conclusion (결론)

This project is an innovative attempt to integrate traditional Bluetooth devices into the IoT environment, aiming to create new value in everyday life by applying smart technology. However, forced wireless firmware updates and port forwarding activities were avoided due to their potential overlap with hacking. The project focuses on exploring remote operation and control of wireless devices using basic Bluetooth functionality.

<br> <br> <br>

## Future Directions (향후 발전 방향)

- **Device Support Expansion:** Broaden development approaches to various Bluetooth-based devices.
- **Function Improvement:** Enhance user interfaces and device-specific functionalities.
- **New Technology Application:** Integrate the latest IoT trends and AI technologies (Computer Vision) for convenient in-home wireless product modeling and device interconnection system via mobile apps.
