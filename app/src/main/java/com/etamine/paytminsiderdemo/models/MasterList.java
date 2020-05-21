package com.etamine.paytminsiderdemo.models;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MasterList {
/* private List<MasterData> masterData;

 public List<MasterData> getMasterData() {
  return masterData;
 }

 public void setMasterData(List<MasterData> masterData) {
  this.masterData = masterData;
 }*/
/*

 public static class MasterData{*/
  private String _id;
  private String name;
  private String type;
  private String slug;
  private String horizontal_cover_image;
  private String city;
  private String venue_name;
  private String venue_date_string;
  private VenueGeolocation venue_geolocation;
  private Group group_id;
  private boolean isFavorite =false;

  public String get_id() {
   return _id;
  }

  public void set_id(String _id) {
   this._id = _id;
  }

  public String getName() {
   return name;
  }

  public void setName(String name) {
   this.name = name;
  }

  public String getType() {
   return type;
  }

  public void setType(String type) {
   this.type = type;
  }

  public String getSlug() {
   return slug;
  }

  public void setSlug(String slug) {
   this.slug = slug;
  }

  public String getHorizontal_cover_image() {
   return horizontal_cover_image;
  }

  public void setHorizontal_cover_image(String horizontal_cover_image) {
   this.horizontal_cover_image = horizontal_cover_image;
  }

  public String getCity() {
   return city;
  }

  public void setCity(String city) {
   this.city = city;
  }

  public String getVenue_name() {
   return venue_name;
  }

  public void setVenue_name(String venue_name) {
   this.venue_name = venue_name;
  }

  public String getVenue_date_string() {
   return venue_date_string;
  }

  public void setVenue_date_string(String venue_date_string) {
   this.venue_date_string = venue_date_string;
  }

  public VenueGeolocation getVenue_geolocation() {
   return venue_geolocation;
  }

  public void setVenue_geolocation(VenueGeolocation venue_geolocation) {
   this.venue_geolocation = venue_geolocation;
  }


    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public class VenueGeolocation{
    private double latitude;
    private double longitude;

   public double getLatitude() {
    return latitude;
   }

   public void setLatitude(double latitude) {
    this.latitude = latitude;
   }

   public double getLongitude() {
    return longitude;
   }

   public void setLongitude(double longitude) {
    this.longitude = longitude;
   }
  }

  public class Group{
      private String name;
      private String icon_img;

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getIcon_img() {
          return icon_img;
      }

      public void setIcon_img(String icon_img) {
          this.icon_img = icon_img;
      }
  }
 }
/*}*/
