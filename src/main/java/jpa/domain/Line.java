package jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Line extends BaseEntity{

	private String color;

	@Column(unique = true)
	private String name;

	@ManyToMany
	private List<Station> stations = new ArrayList<>();

	protected Line() {
	}

	public Line(String name) {
		this.name = name;
	}

	public Line(String name, String color) {
		this.name = name;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void addStations(List<Station> stations) {
		for(Station station : stations) {
			this.addStation(station);
		}
	}

	public void addStation(Station station) {
		this.stations.add(station);
		station.addLine(this);
	}
}
