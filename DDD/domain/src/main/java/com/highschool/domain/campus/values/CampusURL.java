package com.highschool.domain.campus.values;

import co.com.sofka.domain.generic.Identity;

public class CampusURL extends Identity {

    public CampusURL() {}

    private CampusURL(String url) {super(url);}

    public static CampusURL of(String url) {return new CampusURL(url);}
}
