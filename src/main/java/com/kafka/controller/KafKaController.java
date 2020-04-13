package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.model.Employee;

@RestController
@RequestMapping("kafka")
public class KafKaController {
	
	@Autowired
	private KafkaTemplate<String, Employee> kafkaTemplate;
		
	@PostMapping("/producer")
	public String kafkaProducer(Employee employee)  {
		kafkaTemplate.send("infosys",employee);		
		return "Posted";
	}
		
	@KafkaListener(topics = "infosys",  groupId = "group_json", containerFactory = "employeeKafkaListenerFactory")
    public void kafkaConsumer(Employee employee) {
        System.out.println("Consumed Message: " + employee);

    }

}
