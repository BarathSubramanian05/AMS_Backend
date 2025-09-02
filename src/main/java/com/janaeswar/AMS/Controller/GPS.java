package com.janaeswar.AMS.Controller;

import com.janaeswar.AMS.Modal.Location;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/gps")
public class GPS {
    double cent_lat=10.785293;
    double cent_long=78.689322;
    double centre=0.2;

    @PostMapping("/login")
    public String isInPerimeter(@RequestBody Location loc)
    {
        double lat=loc.getLatitude();
        double longitude= loc.getLongitude();
        double dist=haversine(lat,longitude,cent_lat,cent_long);
        return (dist<=centre)?"Success":"Failure";
    }

    @PostMapping("/test")
    public String checking(@RequestBody Location loc)
    {
        System.out.println(loc.latitude+" "+loc.longitude);
        return "Success";
    }

    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}