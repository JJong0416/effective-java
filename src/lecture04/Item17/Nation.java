package lecture04.Item17;

import java.util.List;

public class Nation {
    private final City capital;
    private final List<City> cities;
    private final long population;

    public Nation(City capital, List<City> cities, long population) {
        this.capital = capital;
        this.cities = cities;
        this.population = population;
    }

    public City getCapital() {
        return capital;
    }

    public List<City> getCities() {
        return cities;
    }

    public long getPopulation() {
        return population;
    }
}
