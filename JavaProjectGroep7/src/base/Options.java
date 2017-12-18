package base;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.scene.paint.Color;

@Entity
@Table(name="OPTIONS")
public class Options {

	@Id
	@Column(name="Background_Color")
	private String Background_Color;
	
	@Column(name="Logo_filename")
	private String Logo_filename;
	
	@Column(name="maxTrainingen")
	private int maxTrainingen;

	public String getBackground_Color() {
		return Background_Color;
	}

	public void setBackground_Color(String background_Color) {
		Background_Color = background_Color;
	}

	public String getLogo_filename() {
		return Logo_filename;
	}

	public void setLogo_filename(String logo_filename) {
		Logo_filename = logo_filename;
	}

	public int getMaxTrainingen() {
		return maxTrainingen;
	}

	public void setMaxTrainingen(int maxTrainingen) {
		this.maxTrainingen = maxTrainingen;
	}

	@Override
	public String toString() {
		return "Options [Background_Color=" + Background_Color + ", Logo_filename=" + Logo_filename + ", maxTrainingen="
				+ maxTrainingen + "]";
	}

	
	public Options() {
		super();
	}

	public Options(String background_Color, String logo_filename, int maxTrainingen) {
		super();
		Background_Color = background_Color;
		Logo_filename = logo_filename;
		this.maxTrainingen = maxTrainingen;
	}

	
	
	
	
	
	
	
	
}
