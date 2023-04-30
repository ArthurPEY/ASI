package com.sp.model;

public class Hero {
    private String name;
    private String image_src;
    private String date;
    private String comment;
    private String like;
    private String button;
    private String description;
    private String family;

    private String affinity;


    public Hero(String name, String image_src, String date, String comment, String like, String button, String description, String family, String affinity) {
        this.name = name;
        this.image_src = image_src;
        this.date = date;
        this.comment = comment;
        this.like = like;
        this.button = button;
        this.description = description;
        this.family = family;
        this.affinity = affinity;
    }

    public Hero(String name, String image_src, String description, String family, String affinity) {
        this.name = name;
        this.image_src = image_src;
        this.description = description;
        this.family = family;
        this.affinity = affinity;

        this.date = "test";
        this.comment = "";
        this.like = "";
        this.button = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
