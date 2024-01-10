package com.example.myapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

// 메인 액티비티 클래스
// Main Activity Class
class MainActivity : AppCompatActivity() {
    // 블루투스 어댑터를 위한 변수
    // Variable for Bluetooth Adapter
    private var bluetoothAdapter: BluetoothAdapter? = null

    // 범블비 스피커의 MAC 주소 (예시)
    // MAC address of the Bumblebee Speaker (Example)
    private val speakerMacAddress = "00:11:22:33:44:55"
    
    // 이미지뷰 전역 변수 추가
    // Global variable for ImageView
    private lateinit var imageViewToggle: ImageView

    // 블루투스 장치 검색을 위한 BroadcastReceiver
    // BroadcastReceiver for Bluetooth device discovery
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action: String = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {

                    // 발견된 블루투스 장치 처리
                    // Handling the found Bluetooth device
                    val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC 주소
                    // 여기에서 원하는 장치를 선택하고 연결할 수 있음
                    // You can select and connect to the desired device here
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 블루투스 어댑터 및 이미지뷰 초기화
        // Initialize the Bluetooth Adapter and ImageView
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        imageViewToggle = findViewById(R.id.imageViewToggle)

        imageViewToggle.setOnClickListener {
            toggleBluetoothStatus(imageViewToggle)
            connectAndSendCommandToSpeaker()            
        }

        // 장치가 블루투스를 지원하는지 확인
        // Check if the device supports Bluetooth
        if (bluetoothAdapter == null) {
            informUserBluetoothNotSupported()
        } else {
            updateBluetoothStatus(imageViewToggle)
            imageViewToggle.setOnClickListener {
                toggleBluetoothStatus(imageViewToggle)
                connectAndSendCommandToSpeaker()

                // 만약 MAC 주소를 모를 경우, 주변의 블루투스 장치를 검색
                // If the MAC address is unknown, search for nearby Bluetooth devices
                if (speakerMacAddress.isBlank()) {
                    bluetoothAdapter?.startDiscovery()
                }
            }
        }

        // 블루투스 검색을 위한 BroadcastReceiver 등록
        // Register the BroadcastReceiver for Bluetooth discovery
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }

    // 블루투스가 지원되지 않음을 사용자에게 알리는 함수
    // #Function to inform the user that Bluetooth is not supported
    private fun informUserBluetoothNotSupported() {
        Toast.makeText(this, "블루투스 서비스를 지원할 수 없습니다.", Toast.LENGTH_SHORT).show()
    }

    // 블루투스 상태를 업데이트하는 함수
    // #Function to update the Bluetooth status
    private fun updateBluetoothStatus(imageViewToggle: ImageView) {
        if (bluetoothAdapter?.isEnabled == true) {
            // 블루투스가 활성화되어 있으면 ON 이미지를 표시
            // #If Bluetooth is enabled, display the ON image
            imageViewToggle.setImageResource(R.drawable.ic_on)
        } else {
            // 블루투스가 비활성화되어 있으면 OFF 이미지를 표시
            // #If Bluetooth is not enabled, display the OFF image
            imageViewToggle.setImageResource(R.drawable.ic_off)
        }
    }

    // 블루투스 상태를 토글하는 함수
    // #Function to toggle the Bluetooth status
    private fun toggleBluetoothStatus(imageViewToggle: ImageView) {
        if (bluetoothAdapter?.isEnabled == true) {
            // 블루투스가 활성화되어 있으면 비활성화
            // #If Bluetooth is enabled, then disable it
            bluetoothAdapter?.disable()
            imageViewToggle.setImageResource(R.drawable.ic_off)
        } else {
            // 블루투스가 비활성화되어 있으면 활성화 요청
            // #If Bluetooth is not enabled, then request to enable it
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }

    // 블루투스 소켓을 생성하는 함수
    // #Function to create a Bluetooth socket
    private fun createBluetoothSocket(device: BluetoothDevice): BluetoothSocket? {
        return try {
            val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            device.createRfcommSocketToServiceRecord(uuid)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // 전원 명령을 전송하는 함수
    // #Function to send the power command
    private fun sendPowerCommand(socket: BluetoothSocket, command: String) {
        try {
            val outputStream = socket.outputStream
            outputStream.write(command.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // 스마트 플러그를 통해 스피커의 전원을 켜는 함수
    // #Function to turn on the speaker's power via a smart plug
    private fun turnOnSpeakerViaSmartPlug() {
        // 스레드를 생성하여 네트워크 작업 수행
        // Create a thread to perform network tasks
        Thread {
            try {
                val apiUrl = "http://example-smartplug-api.com/api/plug/on"
                val url = URL(apiUrl)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"

                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 전원이 켜졌을 때의 로직
                    // Logic when powered on
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    companion object {
        // 상수로 블루투스 활성화 요청 코드 정의
        // #Define the request code for Bluetooth activation as a constant
        private const val REQUEST_ENABLE_BT = 1
    }

        // 범블비 스피커와 연결을 시도하고, 실패할 경우 스마트 플러그를 통해 전원을 켜는 함수
        // Function that attempts to connect to the Bumblebee speaker, and if failed, turns on the power through the smart plug
    private fun connectSpeakerOrTurnOnViaSmartPlug() {
        val device = bluetoothAdapter?.getRemoteDevice(speakerMacAddress)
        device?.let {
            val socket = createBluetoothSocket(it)
            socket?.let { btSocket ->
                try {
                    btSocket.connect()
                    // 연결에 성공했으면 해당 로직을 실행
                    // If the connection is successful, execute the logic
                } catch (e: IOException) {
                    // 연결에 실패했으면 스마트 플러그를 통해 전원을 켜는 로직을 실행
                    // If connection fails, execute logic to turn on power through smart plug
                    turnOnSpeakerViaSmartPlug()
                } finally {
                    try {
                        btSocket.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
    
    private fun turnOnSpeakerViaSmartPlug() {
        // 가상의 스마트 플러그 API URL
        // Virtual smart plug API URL
        val apiUrl = "http://example-smartplug-api.com/api/plug/on"

        // 스레드를 생성하여 네트워크 작업 수행
        // Create a thread to perform network tasks
        Thread {
            try {
                val url = URL(apiUrl)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST" 

                // 요청을 전송하고 응답을 받음
                // Send request and receive response
                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 응답이 성공적일 경우, 스피커와의 연결 재시도
                    // If the response is successful, retry connecting to the speaker
                    // connectToSpeaker() // 스피커 연결 함수 호출
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    // API를 호출하여 스피커 상태를 확인하고 UI를 업데이트하는 함수
    // Function to call the API to check the speaker status and update the UI
    private fun updateUIBasedOnSpeakerStatus() {
        Thread {
            try {
                // 가상의 API URL - 스피커의 상태를 확인하는 API
                // #Example API URL - API to check the status of the speaker
                val apiUrl = "http://example-speaker-api.com/api/speaker/status"
                val url = URL(apiUrl)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"

                // API 요청을 전송하고 응답을 받음
                // #Send API request and receive the response
                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 실제 구현에서는 API 응답을 파싱하여 스피커 상태를 파악합니다.
                    // #In actual implementation, parse the API response to determine the speaker status.
                    // 예시 코드는 스피커가 켜져 있다고 가정합니다.
                    // #The example code assumes the speaker is on.
                    val isSpeakerOn = true // 

                    runOnUiThread {
                        // 스피커 상태에 따라 UI 업데이트
                        // #Update UI based on the speaker status
                        val imageViewToggle: ImageView = findViewById(R.id.imageViewToggle)
                        if (isSpeakerOn) {
                            imageViewToggle.setImageResource(R.drawable.ic_off)
                        } else {
                            imageViewToggle.setImageResource(R.drawable.ic_on)
                        }
                    }
                }
            } catch (e: Exception) {
                // 예외 처리
                // #Exception handling
                e.printStackTrace()
            }
        }.start()
    }
}
