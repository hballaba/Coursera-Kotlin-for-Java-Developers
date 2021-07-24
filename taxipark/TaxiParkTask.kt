package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    this.allDrivers.
    filter { driver -> driver !in this.trips.map { trip -> trip.driver }}.
    toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    this.allPassengers.
    filter { passenger -> this.trips.
    filter { trip -> trip.passengers.contains(passenger) }.
    count() >= minTrips
    }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    this.allPassengers.
    filter { passenger -> this.trips.
    filter { trip -> passenger in trip.passengers && trip.driver == driver }.
    count() > 1 }.
    toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    this.allPassengers.
    filter { passenger ->
        this.trips. filter { trip -> trip.passengers.contains(passenger) && trip.discount != null }.count() >
                this.trips.filter { trip -> trip.passengers.contains(passenger) && trip.discount == null }.count()}.
    toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if(this.trips.isEmpty() )
        return null
    val duration = trips.map{ trip -> trip.duration }.toList()
    var set = duration.groupBy { it / 10 }
    val maxSize = set. map{it.value.size}.max()
    val decimal = set.filter { it.value.size == maxSize } .keys.first()
    return IntRange(decimal * 10, decimal * 10 + 9)
}



/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isEmpty() )
        return false
    val totalCost = trips.map { trip ->  trip.cost }.sum()
    val numTopDrivers = allDrivers.size * 0.2


    val costDriver = trips
        .groupBy { it.driver }
        .mapValues { (_, trips) -> trips.sumByDouble { it.cost }}
        .toList()
        .sortedByDescending { (_, value) -> value}.toMap()

    var sum = 0.0
    var numDrivers = 0
    for (value in costDriver.values){
        numDrivers++
        sum += value
        if (sum >= (totalCost * 0.8))
            break ;
    }

    return numDrivers <= numTopDrivers
}