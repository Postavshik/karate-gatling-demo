package performance;

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class PerformanceTest extends Simulation {

   
    val protocol = karateProtocol(
    )
    
    val users = scenario("Get users").exec(karateFeature("classpath:examples/users/users.feature")) 

    setUp(
        users.inject(
            rampUsers(1) during (10 seconds),
            constantUsersPerSec(5) during(10 seconds),
            constantUsersPerSec(10) during(10 seconds),
            constantUsersPerSec(15) during(10 seconds)
        ).protocols(protocol)
    )

}