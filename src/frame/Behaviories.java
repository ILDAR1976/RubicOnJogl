package frame;

import Object3D.Point3D;
import com.jogamp.opengl.GL2;

public final class Behaviories implements DrawObjects {
	//Блок переменных для ротации фигуры
    public boolean Selected = false;
	protected int Rotate = 0;
	protected float RotateX = 0;
	protected float RotateY = 0;
	protected float RotateZ = 0;
	protected float RotateBuffX = 0;
	protected float RotateBuffZ = 0;
	protected float RotateBuffY = 0;
	protected boolean RotateFlag = false;
	protected GL2 gl =  null;
	protected Point3D MainPoint = null;
	protected boolean ActionComplished = false;
	public float[] AR;
	
	public Behaviories(GL2 iGL,Point3D MP){
		gl = iGL;
		MainPoint = MP;
	}
	
	//Блок методов для вращения фигуры вокруг своих осей
	public void ReleaseRotation(){
		switch (Rotate){
		case 1: RotateXYZ();        // Вращаем против часовой стрелки по X 
		if (RotateFlag) 
			if (RotateX > 90) {
				RotateX = 90;
				ActionComplished = true;
			} else RotateX += 0.6f;
		break;
		case 2: RotateXYZ();        // Вращаем против часовой стрелки по Y
		if (RotateFlag) 
			if (RotateY > 90) {
				RotateY = 90;
				ActionComplished = true;
			} else RotateY += 0.6f;
		break;
		case 3: RotateXYZ();        // Вращаем против часовой стрелки по Z
		if (RotateFlag) 
			if (RotateZ > 90){
				RotateZ = 90;
				ActionComplished = true;
			} 
			else RotateZ += 0.6f;
		break;
		case 4: RotateXYZ();        // Вращаем по часовой стрелке по X
		if (RotateFlag) 
			if (RotateX < -90) {
				RotateX = -90;
				ActionComplished = true;
			} else RotateX -= 0.6f;
		break;
		case 5: RotateXYZ();        // Вращаем по часовой стрелке по Y
		if (RotateFlag) 
			if (RotateY < -90) {
				RotateY = -90;
				ActionComplished = true;
			} else RotateY -= 0.6f;
		break;
		case 6: RotateXYZ();        // Вращаем по часовой стрелке по Z
		if (RotateFlag)
			if (RotateZ < -90) {
				RotateZ = -90;
				ActionComplished = true;
			} else RotateZ -= 0.6f;
		break;
		}
	}
	public void RotateX(){ Rotate = 1; };
	public void RotateY(){ Rotate = 2; };
	public void RotateZ(){ Rotate = 3; };
	public void RotateNX(){ Rotate = 4; };
	public void RotateNY(){ Rotate = 5; };
	public void RotateNZ(){ Rotate = 6; };
	public void RotateEnd(){ 
		if (RotateFlag) 
			RotateFlag = false; 
		else 
			RotateFlag = true;
	};
	public void StartRotate(){ 
		RotateFlag = true;
		//ActionComplished = false;
	};
	public void StopRotate(){ 
		RotateX = 0;
		RotateY = 0;
		RotateZ = 0;
		
		RotateFlag = false;
		ActionComplished = false;
	};
	public void RotateXYZ(){
		gl.glRotatef(RotateX, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(RotateY, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(RotateZ, 0.0f, 0.0f, 1.0f);
	}
		
	@Override
	public void draw(int Index) {
	}
	@Override
	public Point3D GetMove() {
		return MainPoint;
	}

	@Override
	public Point3D GetMainPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetPlane(int[] Plane) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public float[] GetRotation() {
		float[] M = new float[3];
		M[0] = RotateX;
		M[1] = RotateY;
		M[2] = RotateZ;
		return M;
	}
	
	@Override
	public void SetRotation(float[] RV) {
		RotateX = RV[0];
		RotateY = RV[1];
		RotateZ = RV[2];
	}

	@Override
	public boolean GetActionFlag() {
		return ActionComplished;
	}

	@Override
	public void SetAR(float[] iAR) {
		AR = iAR;
	}
}
