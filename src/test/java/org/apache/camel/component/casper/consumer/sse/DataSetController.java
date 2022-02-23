package org.apache.camel.component.casper.consumer.sse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.component.casper.consumer.sse.model.block.BlockData;
import org.apache.camel.component.casper.consumer.sse.model.deploy.DeployAcceptedData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class DataSetController {

	private final DataSetService dataSetService;
	ObjectMapper objectMapper = new ObjectMapper();

	public DataSetController(DataSetService dataSetService) {
		this.dataSetService = dataSetService;
		//ObjectMapper objectMapper = new ObjectMapper();
	}
/*
	@GetMapping("/fetch-data-sets")
	public ResponseBodyEmitter fetchData() {
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			List<DataSet> dataSets = dataSetService.findAll();
			try {
				for (DataSet dataSet : dataSets) {
					randomDelay();
					emitter.send(dataSet);
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		executor.shutdown();
		return emitter;
	}
*/
	@GetMapping("/events/main")
	public SseEmitter fetchmain() {
		SseEmitter emitter = new SseEmitter(600000l);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			List<BlockData> dataSets = dataSetService.getBlocks();
			try {
				for (BlockData dataSet : dataSets) {
					randomDelay();
					emitter.send(objectMapper.writeValueAsString(dataSet));
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		executor.shutdown();
		return emitter;
	}

	
	@GetMapping("/events/deploys")
	public SseEmitter fetchdeploys() {
		SseEmitter emitter = new SseEmitter(600000l);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			List<DeployAcceptedData> dataSets = dataSetService.getDeploys();
			try {
				for (DeployAcceptedData dataSet : dataSets) {
					randomDelay();
					emitter.send(objectMapper.writeValueAsString(dataSet));
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		executor.shutdown();
		return emitter;
	}

	/*

	@GetMapping("/events/sigs")
	public SseEmitter fetchsigs() {
		SseEmitter emitter = new SseEmitter(600000l);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			List<DataSet> dataSets = dataSetService.findAll();
			try {
				for (DataSet dataSet : dataSets) {
					randomDelay();
					emitter.send(dataSet);
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		executor.shutdown();
		return emitter;
	}
	*/
	
	private void randomDelay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
