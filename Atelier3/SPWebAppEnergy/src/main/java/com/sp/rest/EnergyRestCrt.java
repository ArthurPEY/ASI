  package com.sp.rest;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  import org.springframework.web.bind.annotation.RestController;

  import com.sp.service.EnergyService;

  @RestController
  public class EnergyRestCrt {
      @Autowired
      EnergyService eService;
      
      @RequestMapping(method=RequestMethod.POST,value="/addlowenergy/{id}")
      public void addLowEnergy(@PathVariable String id) {
    	  eService.addLowEnergy(Integer.parseInt(id));
      }
      

      
  }
