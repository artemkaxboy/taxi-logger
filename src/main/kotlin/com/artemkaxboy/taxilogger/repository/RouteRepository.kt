package com.artemkaxboy.taxilogger.repository

import com.artemkaxboy.taxilogger.entity.Route
import org.springframework.data.mongodb.repository.MongoRepository

interface RouteRepository : MongoRepository<Route, String>
