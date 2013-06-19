package dbadmin

class POI {
        String address
        String x_coordinate
        String y_coordinate
        String JSON
        String type

    static mapping = {
        JSON(type: "text")
    }
    static constraints = {
        address(blank: false, nullable: false)
        x_coordinate(blank: false, nullable: false)
        y_coordinate(blank: false, nullable: false)
        JSON(blank: false, nullable: false)
    }
}
