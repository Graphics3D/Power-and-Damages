package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.ITexture;
import java.awt.Color;
import java.util.Random;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRISphere;
import info.emptycanvas.library.object.RepresentableConteneur;

@SuppressWarnings("serial")
public class Bonus extends RepresentableConteneur {
	Random r = new Random();

	public Bonus(Terrain t) {
		for (int i = 0; i < 10; i++) {
                    Point3D centre = t.calcCposition((r.nextFloat() - 0.5), (r.nextFloat() - 0.5));
			TRISphere s = new TRISphere(centre, 0.01);
			s.texture((ITexture) new TColor(Color.RED));

			s.setMaxX(4);

			s.setMaxY(4);

			add(s);

		}
	}

	public boolean removeBonus(Representable r2) {
		boolean success = false;
		while (!success) {
			try {
				super.remove(r2);
				success = true;
                                if(this.getListRepresentable().isEmpty())
                                    return true;
			} catch (Exception ex) {
				success = false;
			}
		}
                return false;
	}
}
