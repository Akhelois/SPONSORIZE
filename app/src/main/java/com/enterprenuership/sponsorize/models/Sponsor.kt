package com.enterprenuership.sponsorize.models

data class Sponsor(
    var sponsorId: String,
    var sponsorName: String,
    var sponsorCompany: String,
    var sponsorLogo: String,
    var imageHeader: String,
    var sponsorCategory: String,
    var sponsorDescription: String,
    var sponsorCriteria: List<String> = listOf()
)
