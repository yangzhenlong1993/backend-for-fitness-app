package mobile_project.utils;

import java.math.BigDecimal;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

public class Calculator {

	// 计算所在位置和目标位置的距离
	public static double getDistance(double oriLongtitude, double oriLatitude, double desLongtitude,
			double desLatitude) {
		GlobalCoordinates source = new GlobalCoordinates(oriLatitude, oriLongtitude);
		GlobalCoordinates target = new GlobalCoordinates(desLatitude, desLongtitude);

		GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target);
		double distance = geoCurve.getEllipsoidalDistance();
		return distance;

	}

	// 计算用户的信用等级, 结果为1 星到5星
	public static int calUserLevel(int totalCount, int attendance) {
		double a = Double.valueOf(totalCount);
		double b = Double.valueOf(attendance);
		BigDecimal bg = new BigDecimal(b / a);
		double decimal = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		int level = 1;
		for (double i = 0; i <= 1; i = i + 0.25) {
			level = level + 1;
			if (decimal - i > 0 && decimal - (i + 0.25) > 0) {
				continue;
			} else if (decimal - i > 0 && decimal - (i + 0.25) < 0) {
				if (decimal - i > i + 0.25 - decimal) {
					break;
				} else {
					level = level + 1;
					break;
				}
			}
		}
		return level;
	}
}
