package frame;

import Object3D.Point3D;

public interface DrawObjects extends 
    iMovies,
    iRotations {
	public void draw(int Index);
	public Point3D GetMainPoint();
	public void SetPlane(int Plane[]);
}
