package iha.figure;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

import iha.frame.Behaviories;
import iha.frame.DrawObjects;
import iha.Object2D.*;
import iha.Object3D.*;

public class figure implements DrawObjects	{
    protected Behaviories Behavior = null;
    //Блок переменных для обработки позиционирования и перемещения фигуры
    protected Point3D MainPoint = null;  //Главная точка позиционирующая фигуру в сцене
    protected Vector3D MainVector = null; //Вектор направления движения фигуры
    protected Vector3D RotateVector = null; //Вектор направления вращения фигуры
    
    protected float g = 0;
    protected float r = 0;
    
    //Блок переменных для обработки отрисовки фигуры
    protected ArrayList<Vector3D> Vectors3D = null;
    protected ArrayList<Point3D> Points3D = null;
    protected ArrayList<Point> Points = null;
    
    //Блок переменных для обработки отрисовки текстур фигуры
    protected Texture[] Textures = null;
	protected float[] MainTextureCoords = null;
	
	//Блок переменных для отработки связи с контекстом графической сцены
	protected GL2 gl; 
	protected GLU glu = null;
	protected GLUquadric pObj = null;
	
	//Конструкторы
	public figure(GL2 iGL,
			      float iX, 
			      float iY, 
			      float iZ){
		gl = iGL;
		MainPoint = new Point3D(iX,iY,iZ);
		Textures = LoadTextures();
		Behavior =  new Behaviories(gl,MainPoint);
	}

	public figure(GL2 iGL,
		      Point3D P){
		gl = iGL;
		MainPoint = new Point3D(P.x(),P.y(),P.z());
		Textures = LoadTextures();
	}
	
	//Прототип метода для наложения и отрисовки текстур фигуры
	public Texture[] LoadTextures(){
		return null;
	};
	
	@Override
	public void ReleaseRotation() {
		Behavior.ReleaseRotation();
	}
	@Override
	public void RotateX() {
		Behavior.RotateX();
	}
	@Override
	public void RotateY() {
		Behavior.RotateY();
	}
	@Override
	public void RotateZ() {
		Behavior.RotateZ();
	}
	@Override
	public void RotateNX() {
		Behavior.RotateNX();
	}
	@Override
	public void RotateNY() {
		Behavior.RotateNY();
	}
	@Override
	public void RotateNZ() {
		Behavior.RotateNZ();
	}
	@Override
	public void RotateEnd() {
		Behavior.RotateEnd();
	}
	@Override
	public void RotateXYZ() {
		Behavior.RotateXYZ();
	}
	@Override
	public float[] GetRotation() {
		return Behavior.GetRotation();
	}
	@Override
	public void SetRotation(float[] RV) {
		Behavior.SetRotation(RV);
	}
	@Override
	public void StartRotate() {
		Behavior.StartRotate();
	}
	@Override
	public void StopRotate() {
		Behavior.StopRotate();
	}

	@Override
	public void draw(int Index) {
	}
	@Override
	public Point3D GetMove() {
		return Behavior.GetMove();
	}
	public Point3D GetMainPoint()
	{
		return(MainPoint);
	}

	@Override
	public void SetPlane(int[] Plane) {
		Behavior.SetPlane(Plane);
		
	}

	@Override
	public boolean GetActionFlag() {
		return Behavior.GetActionFlag();
	}

	@Override
	public void SetAR(float[] fAR) {
		Behavior.SetAR(fAR);
	}
}
