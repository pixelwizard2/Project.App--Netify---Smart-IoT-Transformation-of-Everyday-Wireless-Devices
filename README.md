# Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices
_비-IoT 무선 장치 IoT 통합을 위한 블루투스 스피커 자동화 제어 시스템 연구(App)_  
[ 작성 중 ~2024.01.10 ]
<p align="right">
  <a href="https://blog.naver.com/pixelwizard/223317548521">
    <img src="https://img.shields.io/badge/한국어%20번역본-03C75A?style=flat-square&logo=Naver&logoColor=white" alt="네이버 블로그">
  </a> </p>  
  
![화면4](https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/29a1e1cc-0f22-453b-97dd-d65fbf48d09f)

## Project Genesis (프로젝트 발단)

The idea originated while conceptualizing a domestic IoT smart home integration project. It was noticed that people commonly use wireless devices without recognizing their unique specifications (API, MAC, etc.). This led to concerns that in the future smart home era, current wireless products unable to remotely interconnect due to lack of technological advancement might become obsolete. To prevent such significant waste of societal resources, this research project was initiated.

**※ Development Period : 2024.01.09 ~ 2024.01.10 (13.5h)**

<br>

## Project Overview (프로젝트 개요)

This project aims to integrate Bluetooth-based wireless products, which lack specific information, into the IoT to become part of the smart home system. This **diverges from the traditional approach of using API, SDK, MAC addresses for IoT wireless integration.** 
<br> <br>
![화면2](https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/7b2b4b43-7b1a-4e14-b5c1-319d6b94af98)
<br> <br>
The project explores how to maximize the wireless capabilities of each device and **make IoT accessible to everyone through an app**. It focuses on implementing numerous ideas in code using Kotlin for Android app development, primarily for research purposes rather than practical app development.

<br>

## Project Target Setting (프로젝트 대상 설정)

The focus is on an **unidentified Bluetooth-based wireless speaker** purchased from a Vietnamese night market.
<br>
<img width="662" alt="화면5" src="https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/a7c49689-312a-4df0-8997-8456c52e9673">
<br> 
It contains basic information like a 3.7V 1200mAh battery and Bluetooth pairing capability, but **lacks crucial details like API and company information**, making it an ideal research subject.

<br>

## Project Direction (프로젝트 목표 방향 )

- **The aim is** to find common household wireless products and **devise various solutions for their IoT integration.** 
- **The goal is** to innovate user experience by integrating everyday Bluetooth devices into the IoT environment,  
                simplifying user interfaces, and **applying IoT technology in daily life.**

<br>

## Research Approach (프로젝트 연구 방향)

Smartphones can easily connect to wireless products through Bluetooth with a single button press. However, devices like Bluetooth speakers **require manual interaction for connection**, especially for new or shared products. 
<br>
<img width="914" alt="화면6" src="https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/c5c3f241-4a9a-4043-9c68-9e524781ce64">
<br>
The project **aims to develop a mechanism where wireless products can be turned on or off upon user request through an app, and the app automatically registers and connects to the device.**

<br>

## Technical Stack and Libraries (기술 스택 및 라이브러리)

- **Development Environment :** Android Studio, Kotlin
- **UI Design/Planning :** XML-based user interface with simple ON/OFF switch
- **Bluetooth Connection and Management :** Android Bluetooth API
- **Firmware Management :** FOTA (Firmware Over The Air) for remote updates
- **Smart Plug Control :** Using HTTP protocol-based RESTful API

<br>

## Bluetooth Connection and Control Logic (블루투스 연결 및 제어 로직)

- **Initial Setup :** The phone's Bluetooth automatically activates upon app launch.
- **Connection Process :** Sequentially implementing Bluetooth search and connection logic in MainActivity.kt.
- **Firmware Auto-Check :** Confirming low power mode operation of the speaker and updating firmware if necessary.
- **Smart Plug Integration :** Indirect power device control through smart plugs if direct control is unfeasible.

<br>

## Project Attempts (프로젝트 시도 사항)
<br>
<img width="1094" alt="화면7" src="https://github.com/pixelwizard2/Project.App--Netify---Smart-IoT-Transformation-of-Everyday-Wireless-Devices/assets/138272416/05c4d82e-751d-4722-b39a-baf623a668aa">  

- **Step 1 :** Develop a system where the app finds and connects to a low-power Bumblebee Bluetooth speaker.
- **Step 2 :** Automatically check for low-power ON/OFF capability in firmware.
- **Step 3 :** If unavailable, remotely upgrade the firmware to control the speaker's power.
- **Step 4 :** As soon as the app runs, the process from 'steps 1 to 3' is automatically carried out in the code unit, and if the power is not turned on, it requests Bluetooth location and permission and accesses a smart plug located adjacent to the device (bumblebee speaker).  
By forcing the power on, the power of the Bumblebee Bluetooth speaker operates forcely.
- **Step 5 :** If the power is turned on, the smartphone app screen switches. When you press the power button again on the changed screen, the screen changes and the wireless device is commanded to turn off at the same time.
- **Step 6 :** Turn off the external power and switch to low-power mode (Firmware capable of low-power switching is pre-installed when processed step 3).

<br>

## Challenges and solutions (문제점 및 해결 방법)

- **Technical Limitation :** Currently, remote control of wireless devices without power (Bluetooth off) is not feasible without advanced design for constant low-power state maintenance, similar to TV remotes.
- **Alternative Approaches :** Indirect control using smart plugs, custom commands, FOTA(Firmware Over The Air) for firmware updates, or equivalent methods (port forwarding, remote access settings).

<br>

## Conclusion (결론)

This project is an innovative attempt to integrate traditional Bluetooth devices into the IoT environment, aiming to create new value in everyday life by applying smart technology. However, forced wireless firmware updates and port forwarding activities were avoided due to their potential overlap with hacking. The project focuses on exploring remote operation and control of wireless devices using basic Bluetooth functionality.

<br>

## Future Directions (향후 발전 방향)

- **Device Support Expansion:** Broaden development approaches to various Bluetooth-based devices.
- **Function Improvement:** Enhance user interfaces and device-specific functionalities.
- **New Technology Application:** Integrate the latest IoT trends and AI technologies (Computer Vision) for convenient in-home wireless product modeling and device interconnection system via mobile apps.
