package com.router;

import javax.inject.Singleton;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import com.beans.*;
import com.jackson.classes.*;
import com.processors.UserProcessor;

@Singleton
@ContextName("_camelContext1")
public class Builder extends RouteBuilder{
	@Override
	public void configure() throws Exception {
		DataFormat jackson = new JacksonDataFormat(User.class);
		
		from("file:files/incoming?noop=true")
		.unmarshal(jackson)
		.process(new UserProcessor())
		.marshal(jackson)
		.setHeader("destinations", constant("file:files/outgoing/backup/backup1?fileName=${headers.FinalFileName}.json,file:files/outgoing/backup/backup2?fileName=${headers.FinalFileName}.json"))
		.recipientList(header("destinations"))
		.choice()
			.when(method(ValidatorBean.class, "validateGender"))
				.toD("file:files/outgoing/Male?fileName=${headers.FinalFileName}.json")
			.otherwise()
				.toD("file:files/outgoing/Female?fileName=${headers.FinalFileName}.json")
		.end();
		
		from("quartz:quartzTimer?trigger.repeatCount=3&trigger.repeatInterval=10000")
		.bean(DatesBean.class, "getCurrentDate")
		.log("Primera ejecucion metodo bean(class<T>): ${body}")
		.to("bean:messageBean?method=getMessage")
		.log("${body}")
		.bean("randomNumbers", "getRandom")
		.log("Random value with CDI: ${body}");
	}
	
}
