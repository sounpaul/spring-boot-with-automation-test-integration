package com.cg.employee.simulation

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class EmployeeServiceSimulation extends Simulation {

  val conf: Config = ConfigFactory.load();
  val env: String = conf.getString("environment")
  println(s"Simulation will be executed in = $env")
  val tps = Integer.getInteger("users", 1).toDouble
  val duration = Integer.getInteger("duration", 1)
  println(s"TPS = $tps")
  println(s"Duration = $duration")

  var baseURL: String =
    if (conf.hasPath("employee_service_" + env)) {
      conf.getString("employee_service_" + env)
    } else conf.getString("employee_service")

  val headers = Map("Content-Type" -> "application/json")
  println(s"Header = $headers")
  val httpBaseConf = http.baseUrl(baseURL)
  println(s"Base URL = $baseURL")

  val GET_EMPLOYEE_BY_FIRSTNAME = scenario("GET EMPLOYEE BY FIRSTNAME SIMULATION")
    .exec(http("GET EMPLOYEE BY FIRSTNAME SIMULATION")
      .get("/api/getEmployee/firstName")
      .queryParam("first_name", "TEST8")
      .check(status is 200))

  val GET_EMPLOYEE_BY_LASTNAME = scenario("GET EMPLOYEE BY LASTNAME SIMULATION")
    .exec(http("GET EMPLOYEE BY LASTNAME SIMULATION")
      .get("/api/getEmployee/lastName")
      .queryParam("last_name", "TEST6")
      .check(status is 200))

  val GET_EMPLOYEE_BY_ID = scenario("GET EMPLOYEE BY ID SIMULATION")
    .exec(http("GET EMPLOYEE BY ID SIMULATION")
      .get("/api/getEmployee/id")
      .queryParam("id", "1286")
      .check(status is 200))

  val UPDATE_EMPLOYEE_REQUEST_FEEDER = csv("data\\UPDATE_EMPLOYEE_FEEDER.csv").circular
  val UPDATE_EMPLOYEE = scenario("UPDATE SERVICE SIMULATION")
    .feed(UPDATE_EMPLOYEE_REQUEST_FEEDER)
    .exec(http("UPDATE SERVICE SIMULATION")
      .put("/api/updateEmployee")
      .queryParam("id", "1372")
      .headers(headers)
      .body(ElFileBody("./json/update_employee.json"))
      .check(status is 200))

  setUp(GET_EMPLOYEE_BY_FIRSTNAME.inject(constantUsersPerSec(tps) during (duration seconds)),
    GET_EMPLOYEE_BY_LASTNAME.inject(constantUsersPerSec(tps) during (duration seconds)),
    GET_EMPLOYEE_BY_ID.inject(constantUsersPerSec(tps) during (duration seconds)),
    UPDATE_EMPLOYEE.inject(constantUsersPerSec(tps) during (duration seconds))).protocols(httpBaseConf)

}
