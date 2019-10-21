package com.joaomariajaneiro.neechathon.bootstrap;

import com.google.common.collect.ImmutableList;
import com.joaomariajaneiro.neechathon.model.*;
import com.joaomariajaneiro.neechathon.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ImportSampleData implements ImportData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;


    private PasswordEncoder passwordEncoder;

    public ImportSampleData(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void importSampleData() {


        System.out.println("Initializing data......");

        Team neec = new Team();
        neec.setName("neec");
        teamRepository.save(neec);
        
        User admin = new User();
        admin.setEmail("admin@admin.com").setGitHub("URL").setLinkedIn("URL!").setPassword(passwordEncoder.encode("Mdfaugcj5V")).setTeam(neec).setUsername("admin");
        userRepository.save(admin);

        Project project1 = new Project();
        project1.setDescription("admin").setTitle("admin").setGitHubURL("admin").setTeam(neec).setTeamName(neec.getName());
        projectRepository.save(project1);

        List<User> neec_users = neec.getUsers();
        neec_users.add(admin);
        neec.setUsers(neec_users);
        neec.setProject(project1);
        neec.setAdmin(true);
        teamRepository.save(neec);


        productRepository.save(new Product().setName("Led 5mm Red").setPrice(1L).setQuantity(53).setDescription("Led que emite luz de cor vermelha.").setImage_path("https://www.boxelectronica.com/116-large_default/led-5mm-vermelho.jpg"));
        productRepository.save(new Product().setName("Led 5mm yellow").setPrice(1L).setQuantity(161).setDescription("Led que emite luz de cor amarela.").setImage_path("https://www.boxelectronica.com/120-large_default/led-5mm-amarelo.jpg"));
        productRepository.save(new Product().setName("Led 5mm Green").setPrice(1L).setQuantity(52).setDescription("Led que emite luz de cor verde.").setImage_path("https://www.boxelectronica.com/119-large_default/led-5mm-verde.jpg"));
        productRepository.save(new Product().setName("Led 5mm Blue").setPrice(1L).setQuantity(46).setDescription("Led que emite luz de cor azul.").setImage_path("https://www.boxelectronica.com/124-large_default/led-5mm-blue.jpg"));
        productRepository.save(new Product().setName("Led 5mm White").setPrice(1L).setQuantity(95).setDescription("Led que emite luz de cor branca.").setImage_path("https://www.boxelectronica.com/123-large_default/led-5mm-branco.jpg"));
        productRepository.save(new Product().setName("Led RGB").setPrice(1L).setQuantity(20).setDescription("Led que emite luz de cor vermelha, verde ou azul.").setImage_path("https://www.boxelectronica.com/123-large_default/led-5mm-branco.jpg"));
        productRepository.save(new Product().setName("Button Switch 6X6X5mm").setPrice(5L).setQuantity(40).setDescription("Controla o teu projecto com este butão.").setImage_path("https://www.boxelectronica.com/330-large_default/botao-de-pressao-6x6x5mm.jpg"));
        productRepository.save(new Product().setName("Buzzer 5v").setPrice(5L).setQuantity(32).setDescription("Emite um som através de uma certa frequência").setImage_path("https://www.boxelectronica.com/4103-large_default/buzzer-5v.jpg"));
        productRepository.save(new Product().setName("20cm Jumper Wire MM").setPrice(1L).setQuantity(100).setDescription("Liga os componentes do teu projecto com estes fios. As duas pontas são para enfiar.").setImage_path("https://www.boxelectronica.com/808-large_default/20pcs-20cm-jumper-wire-mm.jpg"));
        productRepository.save(new Product().setName("20cm Jumper Wire MF").setPrice(1L).setQuantity(100).setDescription("Liga os componentes do teu projecto com estes fios. Uma ponta é para enfiar e a outra para ser enfiada.").setImage_path("https://www.boxelectronica.com/601-large_default/20pcs-20cm-jumper-wire-mf.jpg"));
        productRepository.save(new Product().setName("20cm Jumper Wire FF").setPrice(1L).setQuantity(100).setDescription("Liga os componentes do teu projecto com estes fios. As duas pontas são para ser enfiadas.").setImage_path("https://www.boxelectronica.com/600-large_default/20pcs-20cm-jumper-wire-ff.jpg"));
        productRepository.save(new Product().setName("Resistência 10 ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 10 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 22 ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 22 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 47 ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 47 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 100 ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 100 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 220 ohms").setPrice(1L).setQuantity(267).setDescription("Resistência 220 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 270 ohms").setPrice(1L).setQuantity(47).setDescription("Resistência 270 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 470 ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 470 ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 1K ohms").setPrice(1L).setQuantity(222).setDescription("Resistência 1K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 2K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 2K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 4K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 4K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 10K ohms").setPrice(1L).setQuantity(217).setDescription("Resistência 10K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 22K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 22K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 47K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 47K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 100K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 100K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 220K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 220K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 470K ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 470K ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Resistência 1M ohms").setPrice(1L).setQuantity(60).setDescription("Resistência 1M ohms").setImage_path("https://www.boxelectronica.com/2662-large_default/kit-480-resistencias-1-4.jpg"));
        productRepository.save(new Product().setName("Holder 4 AA Batteries - R06 w/wires").setPrice(10L).setQuantity(3).setDescription("Fonte de energia elétrica através de pilhas. (Usa 4 pilhas AA)").setImage_path("https://www.boxelectronica.com/777-large_default/suporte-4-pilhas-aa-r6-c-fios.jpg"));
        productRepository.save(new Product().setName("Holder 2 AA Batteries - R06 w/wires").setPrice(10L).setQuantity(21).setDescription("Fonte de energia elétrica através de pilhas. (Usa 2 pilhas AA)").setImage_path("https://www.boxelectronica.com/779-large_default/holder-2-aa-batteries-r06-w-wires.jpg"));
        productRepository.save(new Product().setName("Holder 5 AA Batteries").setPrice(10L).setQuantity(2).setDescription("Fonte de energia elétrica através de pilhas. (Usa 5 pilhas AA)").setImage_path("https://www.boxelectronica.com/1000-large_default/holder-5-aa-batteries.jpg"));
        productRepository.save(new Product().setName("Holder 1 CR2025 Batteries").setPrice(10L).setQuantity(2).setDescription("Fonte de energia elétrica através de pilhas. (Usa 1 pilha CR2025)").setImage_path("https://www.boxelectronica.com/1000-large_default/holder-5-aa-batteries.jpg"));
        productRepository.save(new Product().setName("Breadboard 840").setPrice(20L).setQuantity(22).setDescription("Junta todos os componentes do teu projecto nesta breadboard.").setImage_path("https://www.boxelectronica.com/219-large_default/breadboard-840.jpg"));
        productRepository.save(new Product().setName("HC-SR04 - Ultrasonic Range Finder").setPrice(15L).setQuantity(10).setDescription("Emite sinais ultrassónicos que refletem no objeto a ser atingido e retornam ao sensor, indicando a distância ao alvo.").setImage_path("https://www.boxelectronica.com/53-large_default/hc-sr04-sensor-de-ultrasons.jpg"));
        productRepository.save(new Product().setName("LDR - Light Controlled Resistor").setPrice(5L).setQuantity(15).setDescription("A resistência do LDR varia de acordo com a intensidade da luz. Quanto mais luz incidir sobre o componente, menor a resistência.").setImage_path("https://www.boxelectronica.com/313-large_default/sensor-de-luz-ldr-5mm-gl5528.jpg"));
        productRepository.save(new Product().setName("Acelerómetro e Giroscópio").setPrice(30L).setQuantity(15).setDescription("3 eixos 6DOF GY-521 MPU-6050. Este componente possui um giroscópio, acelerómetro e termómetro.").setImage_path("https://www.boxelectronica.com/2917-large_default/acelerometro-e-giroscopio-3-eixos-6dof-gy-521-mpu-6050.jpg"));
        productRepository.save(new Product().setName("HC-05 Bluetooth").setPrice(30L).setQuantity(10).setDescription("O Módulo Bluetooth HC-05 permite que um dispositivo possa comunicar com outro dispositivo através de rede sem fios.").setImage_path("https://www.boxelectronica.com/476-large_default/hc-05-bluetooth.jpg"));
        productRepository.save(new Product().setName("Roda 68mm Com Motor DC 3-6V").setPrice(15L).setQuantity(4).setDescription("Dá movimento ao teu protótipo.").setImage_path("https://www.boxelectronica.com/273-large_default/roda-68mm-com-motor-dc-3-6v-para-robot.jpg"));
        productRepository.save(new Product().setName("DTH11 Temperature & Humidity").setPrice(15L).setQuantity(5).setDescription("Mede a temperatura e humidade do espaço envolvente.").setImage_path("https://www.boxelectronica.com/1144-large_default/dth11-humidade-e-temperatura.jpg"));
        productRepository.save(new Product().setName("BMP180 Digital Barometric Pressure").setPrice(20L).setQuantity(5).setDescription("Mede a pressão atmosférica").setImage_path("https://www.boxelectronica.com/42-large_default/bmp180-pressao-digital-barometrica.jpg"));
        productRepository.save(new Product().setName("PIR Motion Sensor HC-SR501").setPrice(15L).setQuantity(15).setDescription("Permite detetar movimento de objetos na área envolvente.").setImage_path("https://www.boxelectronica.com/52-large_default/sensor-de-movimento-pir-hc-sr501.jpg"));
        productRepository.save(new Product().setName("Grove - Sensor de qualidade do ar V1.3").setPrice(50L).setQuantity(5).setDescription("Serve para testar a qualidade do ar de interiores. Os principais gases detectados são monoxido de carbono, álcool, acetona, formaldeído entre outros.  ").setImage_path("https://www.boxelectronica.com/2270-large_default/grove-sensor-de-qualidade-do-ar-v13.jpg"));
        productRepository.save(new Product().setName("Receptor IR - GP1UX511QS").setPrice(5L).setQuantity(5).setDescription("Lê a frequência de sinais infravermelhos").setImage_path("https://www.boxelectronica.com/2913-large_default/receptor-ir-gp1ux511qs.jpg"));
        productRepository.save(new Product().setName("Remote Control Infrared").setPrice(15L).setQuantity(3).setDescription("Comando com 20 botões, recetor que comunicam por infravermelhos e led de infravermelhos.").setImage_path("https://www.boxelectronica.com/731-large_default/kit-comando-infravermelhos-e-modulo-recetor-ir.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 1pF").setPrice(1L).setQuantity(20).setDescription("Condensador 1pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 5pF").setPrice(1L).setQuantity(20).setDescription("Condensador 5pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 6pF").setPrice(1L).setQuantity(20).setDescription("Condensador 6pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 10pF").setPrice(1L).setQuantity(20).setDescription("Condensador 10pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 15pF").setPrice(1L).setQuantity(20).setDescription("Condensador 15pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 20pF").setPrice(1L).setQuantity(20).setDescription("Condensador 20pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 22pF").setPrice(1L).setQuantity(20).setDescription("Condensador 22pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 30pF").setPrice(1L).setQuantity(20).setDescription("Condensador 30pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 33pF").setPrice(1L).setQuantity(20).setDescription("Condensador 33pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 47pF").setPrice(1L).setQuantity(20).setDescription("Condensador 47pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 68pF").setPrice(1L).setQuantity(20).setDescription("Condensador 68pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 100pF").setPrice(1L).setQuantity(20).setDescription("Condensador 100pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 220pF").setPrice(1L).setQuantity(20).setDescription("Condensador 220pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 330pF").setPrice(1L).setQuantity(20).setDescription("Condensador 330pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 470pF").setPrice(1L).setQuantity(20).setDescription("Condensador 470pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 680pF").setPrice(1L).setQuantity(20).setDescription("Condensador 680pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 1000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 1000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 2200pF").setPrice(1L).setQuantity(20).setDescription("Condensador 2200pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 3300pF").setPrice(1L).setQuantity(20).setDescription("Condensador 3300pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 4700pF").setPrice(1L).setQuantity(20).setDescription("Condensador 4700pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 10000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 10000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 22000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 22000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 33000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 33000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 47000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 47000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Condensador cerâmico 100000pF").setPrice(1L).setQuantity(20).setDescription("Condensador 100000pF").setImage_path("https://www.boxelectronica.com/817-large_default/condensador-ceramico.jpg"));
        productRepository.save(new Product().setName("Electromyography (EMG) Sensor").setPrice(40L).setQuantity(10).setDescription("Purpose-built sensor for muscle activity measurement.").setImage_path("https://plux.info/361-large_default/electromyography-emg-sensor.jpg"));
        productRepository.save(new Product().setName("Electroencephalography (EEG) Sensor").setPrice(40L).setQuantity(10).setDescription("Purpose-built sensor for brain activity measurement.").setImage_path("https://plux.info/373-large_default/electroencephalography-eeg-sensor.jpg"));
        productRepository.save(new Product().setName("Electrocardiography (ECG) Sensor").setPrice(40L).setQuantity(10).setDescription("Purpose-built sensor to measure the electrical activity of the heart.").setImage_path("https://plux.info/364-large_default/electrocardiography-ecg-sensor.jpg"));
        productRepository.save(new Product().setName("Accelerometer (ACC) Sensor").setPrice(40L).setQuantity(10).setDescription("3-axis accelerometer for motion measurement.").setImage_path("https://plux.info/376-large_default/accelerometer-acc-sensor.jpg"));
        productRepository.save(new Product().setName("BITalino R-IoT").setPrice(300L).setQuantity(10).setDescription("Full-featured 9DoF wireless IMU in a stamp-sized package with direct Open Sound Control (OSC) streaming over WiFi.").setImage_path("https://plux.info/1079-large_default/bitalino-r-iot.jpg"));
        productRepository.save(new Product().setName("1-Lead Electrode Cable").setPrice(15L).setQuantity(10).setDescription("Individual cable to connect the sensor pins (IN+, IN-, and REF) to the electrodes.").setImage_path("https://plux.info/450-large_default/1-lead-electrode-cable.jpg"));
        productRepository.save(new Product().setName("Arduino Sensor Cable").setPrice(15L).setQuantity(10).setDescription("Cable assembly used to connect the BITalino sensors to the Arduino main board.").setImage_path("https://plux.info/737-large_default/arduino-sensor-cable.jpg"));
        productRepository.save(new Product().setName("Raspberry Pi 3 B+").setPrice(250L).setQuantity(11).setDescription("O Raspberry Pi 3 Model B+ tem um processador Quad-Core de 64 bits de 1,4 GHz, dual-band de 2,4 GHz e Wireless LAN de 5GHz, Bluetooth 4.2/BLE.").setImage_path("https://www.boxelectronica.com/3382-large_default/raspberry-pi-3-model-b-14ghz-1gb-com-wifi-24-5ghz-bluetooth-42-poe.jpg"));
        productRepository.save(new Product().setName("Arduino").setPrice(150L).setQuantity(10).setDescription("Arduino é uma plataforma de prototipagem eletrônica de hardware livre e de placa única").setImage_path("https://www.boxelectronica.com/2125-large_default/arduino-uno-r3.jpg"));
        productRepository.save(new Product().setName("Pilhas AA").setPrice(5L).setQuantity(20).setDescription("Fornece energia ao teu projecto").setImage_path("https://www.boxelectronica.com/4043-large_default/4-pilhas-aa-r6-15v-varta-superlife.jpg"));
        productRepository.save(new Product().setName("Pilhas CR2025").setPrice(5L).setQuantity(30).setDescription("Fornece energia ao teu projecto").setImage_path("https://www.boxelectronica.com/4090-large_default/pilha-lithium-cr2032-3v.jpg"));
        productRepository.save(new Product().setName("Servos (com ou sem rodas)").setPrice(30L).setQuantity(9).setDescription("Adiciona movimento ao teu projecto com este componente rotativo (com ou sem roda)").setImage_path("https://www.boxelectronica.com/3350-large_default/feetech-fs90r-micro-continuous-rotation-servo.jpg"));
        productRepository.save(new Product().setName("Mini Interruptor").setPrice(5L).setQuantity(71).setDescription("Mini Interruptor SS12D00G3 de 2 Posições SPDT 3 Pinos").setImage_path("https://www.electrofun.pt/4857-large_default/mini-interruptor-ss12d00g3-2-posicoes-spdt-3-pinos.jpg"));


        System.out.println("All data was initialized");
    }
}
