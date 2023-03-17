package org.bougainvilleas.spring.messagingstompwebsocket;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	private SimpMessagingTemplate simpMessagingTemplate;

	public GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

	@GetMapping("/hello2")
	public ResponseEntity<String> greeting2() throws Exception {
		Thread.sleep(1000); // simulated delay
		simpMessagingTemplate.convertAndSend("/topic/greetings",new Greeting("Hello, " + HtmlUtils.htmlEscape("caddy") + "!"));
		return ResponseEntity.ok(null);
	}

}
