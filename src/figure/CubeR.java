package figure;

import figure.BlockR;
import model.CubeMatrix;
import com.jogamp.opengl.GL2;

public class CubeR extends figure{
	private int plane[];
	private BlockR MatrixCube[][][] = new BlockR[4][4][4];	
	private CubeMatrix CM = null;
	
    public CubeR(GL2 iGL, float iX, float iY, float iZ) {
  	  super(iGL, iX, iY, iZ);
  	  CM = new CubeMatrix();
  	  //CM.PrintCube();
  	  //CM.Print(CM.Cube3D.Matrix);
  	  plane = new int[3];
  	  
      for (int i = 1; i < 4; i++) {
    	  for (int j = 1; j < 4; j++) {
    		  for (int k = 1; k < 4; k++) {
    			  
    			  MatrixCube[i][j][k] = new BlockR(gl,
    					  (float)(i * 2 - 4f),
    					  (float)(j * 2 - 4f),
    					  (float)(k * 2 - 4f)
    					  );
    		  }
    	  }
	  }

    }
   
	@Override
	public void draw(int Index) {
	    boolean flag = false;
	    gl.glPushMatrix();     
	    MainPoint = GetMove();
	    gl.glTranslatef(MainPoint.x(), MainPoint.y(), MainPoint.z()); 
	    RotationCube();
	      gl.glPushMatrix();
		      if ((plane[0] == -1)||(plane[0] == -2)||(plane[0] == -3)) {
				RotateX();
			  }
		      if ((plane[0] == 1)||(plane[0] == 2)||(plane[0] == 3)) {
				RotateNX();
			  }
	
		      if ((plane[1] == -1)||(plane[1] == -2)||(plane[1] == -3)) {
				RotateY();
			  }
		      if ((plane[1] == 1)||(plane[1] == 2)||(plane[1] == 3)) {
				RotateNY();
			  }
	
		      if ((plane[2] == -1)||(plane[2] == -2)||(plane[2] == -3)) {
				RotateZ();
			  }
		      if ((plane[2] == 1)||(plane[2] == 2)||(plane[2] == 3)) {
				RotateNZ();
			  }
		      boolean GAF = GetActionFlag();
		      //System.out.println(GAF);
		      if ((GAF) && ((plane[0]!=0)|| (plane[1]!=0) || (plane[2]!=0))) {
		    	  plane[1] = -plane[1];
		    	  CM.Rotate(plane);
		    	  for (int i = 0; i < 3; i++) plane[i] = 0;
				  //CM.Print(CM.Cube3D.Matrix);
				  StopRotate();
				
		      }
	          if ((plane[0] != 0)||(plane[1] != 0)||(plane[2] != 0) && !GAF) {StartRotate();};
	  
	          ReleaseRotation();
		      ShowMatrix(flag,Index,plane,true);
	      gl.glPopMatrix();
	      
	      gl.glPushMatrix();
	        ShowMatrix(flag,Index,plane,false);
	      gl.glPopMatrix();     
	    
	    gl.glPopMatrix();     
	}
	public void ShowMatrix(boolean flag,int Index,int[] plane,boolean lights)
	{
	      for (int i = 1; i < 4; i++) {
	    	  for (int j = 1; j < 4; j++) {
	    		  for (int k = 1; k < 4; k++) {
	    			    if (i * i == plane[0] * plane[0] ) flag = true;
	    			    if (j * j == plane[1] * plane[1] ) flag = true;
	    			    if (k * k == plane[2] * plane[2] ) flag = true;
	    			     
	    			    if ((lights)?flag:!flag) {
		    			    Index = (int)(i * 1000 + j * 100 + k * 10);
		    			    gl.glPushMatrix();
	 	    			    gl.glTranslatef(MatrixCube[i][j][k].MainPoint.x(),
	    			    		            MatrixCube[i][j][k].MainPoint.y(),
	    			    		            MatrixCube[i][j][k].MainPoint.z()
	    			    		            );
	 	    			    MatrixCube[i][j][k].DrawBlock(gl, CM.Cube3D.Matrix[i][j][k].b, Index);
		    			    gl.glPopMatrix();
	    			    }
	    			    flag = false;
	    		  }
	    	  }
		  } 
	}
	public void SetPlane(int Plane[])
	{
		if (!GetActionFlag())
		for (int i = 0; i < 3; i++) plane[i] = Plane[i]; 
	}

	public void RotationCube()
	{
        gl.glRotatef(Behavior.AR[0], 1.0f, 0.0f, 0.0f); // rotate about the x-axis
        gl.glRotatef(Behavior.AR[1], 0.0f, 1.0f, 0.0f); // rotate about the y-axis
        gl.glRotatef(Behavior.AR[2], 0.0f, 0.0f, 1.0f); // rotate about the z-axis
	}
}
