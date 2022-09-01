package com.bsuir.health_analyzer.controller;

import com.bsuir.health_analyzer.Auth;
import com.bsuir.health_analyzer.model.HealthModel;
import com.bsuir.health_analyzer.service.HealthService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/health")
@CrossOrigin
public class HealthController {
    @Autowired
    private HealthService healthService;

    @PostMapping("/send")
    public ResponseEntity<?> Send(@RequestBody String bodyString) throws JSONException {
        JSONObject body = new JSONObject(bodyString);
       // if (Auth.Verify(body)) new ResponseEntity<>(HttpStatus.FORBIDDEN);

        HealthModel healthModel = new HealthModel();
        healthModel.setName(body.getString("name"));
        healthModel.setCpu(body.getInt("cpu"));
        healthModel.setGpu(body.getInt("gpu"));
        healthModel.setRam(body.getInt("ram"));
        healthModel.setTime(new Date());
        healthService.Save(healthModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_last")
    public ResponseEntity<?> GetLast() throws JSONException {
        JSONObject body = new JSONObject();
        //if (Auth.Verify(body)) new ResponseEntity<>(HttpStatus.FORBIDDEN);

        JSONObject response = new JSONObject();
        HealthModel health = healthService.GetLast(body.getString("name"));

        response.put("cpu", health.getCpu());
        response.put("gpu", health.getGpu());
        response.put("ram", health.getRam());
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @PostMapping("/get_history")
    public ResponseEntity<?> GetHistory(@RequestBody String bodyString) throws JSONException {
        JSONObject body = new JSONObject(bodyString);
       // if (Auth.Verify(body)) new ResponseEntity<>(HttpStatus.FORBIDDEN);

        List<HealthModel> history = healthService.GetAllByName(body.getString("name"));
        JSONArray response = new JSONArray();
        for (int i = 0; i < Math.min(history.size(), 30); i++) {
            JSONObject healthJson = new JSONObject();
            healthJson.put("cpu", history.get(i).getCpu());
            healthJson.put("gpu", history.get(i).getGpu());
            healthJson.put("ram", history.get(i).getRam());
            healthJson.put("time", history.get(i).getTime().toString());
            response.put(healthJson);
        }

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @GetMapping ("/get_clients")
    public ResponseEntity<?> GetAllClients() {
        JSONObject body = new JSONObject();
        //if (Auth.Verify(body)) new ResponseEntity<>(HttpStatus.FORBIDDEN);

        JSONArray response = new JSONArray();
        for (String name : healthService.GetAllNames()) {
            JSONObject healthJson = new JSONObject();
            HealthModel health = healthService.GetLast(name);

            healthJson.put("name", health.getName());
            healthJson.put("cpu", health.getCpu());
            healthJson.put("gpu", health.getGpu());
            healthJson.put("ram", health.getRam());
            healthJson.put("time", health.getTime().toString());
            response.put(healthJson);
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
    @PostMapping("/task_execution")
    public ResponseEntity<?> getClientsByFilters(@RequestBody String bodyString) throws JSONException {
        JSONObject body = new JSONObject(bodyString);
         List<HealthModel> filteredClients = healthService.getClientsByFilters(body.getInt("cpu"),body.getInt("gpu"),body.getInt("ram"));
        JSONArray response = new JSONArray();
        filteredClients.forEach(current->{
            JSONObject healthJson = new JSONObject();
            healthJson.put("cpu", current.getCpu());
            healthJson.put("gpu", current.getGpu());
            healthJson.put("ram", current.getRam());
            response.put(healthJson);
        });
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}

