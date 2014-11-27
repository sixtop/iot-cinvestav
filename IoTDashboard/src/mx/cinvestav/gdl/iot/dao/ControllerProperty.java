package mx.cinvestav.gdl.iot.dao;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "controller_prop")
public class ControllerProperty
{
	@Persistent
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Persistent
	private String name;

	@Persistent
	private String value;
}