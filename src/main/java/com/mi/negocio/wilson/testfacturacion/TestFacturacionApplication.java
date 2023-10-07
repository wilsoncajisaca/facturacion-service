package com.mi.negocio.wilson.testfacturacion;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Facturacion API", version = "1.0", description = "Facturacion Service"))
@Slf4j
public class TestFacturacionApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(TestFacturacionApplication.class, args);
		}catch (Exception cause){
			if(!Objects.equals(cause.getClass().getName(),
					"org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")
					&& log.isErrorEnabled()){
				log.error(
						"********************************Ha ocurrido una exception****************************");
				log.error("Exception: " + cause);
				log.error("Root Cause: " + ExceptionUtils.getRootCause(cause).toString());
			}
		}
	}
}
