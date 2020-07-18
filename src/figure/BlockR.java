package figure;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Object2D.Point;
import Object3D.Point3D;
import Object3D.Vector3D;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class BlockR extends figure{
	private int[] PlaneColorMask;
	public BlockR(GL2 iGL,float X,float Y,float Z) {
  	  super(iGL, X, Y, Z);

	  Vectors3D = new ArrayList<Vector3D>();
	  Points3D = new ArrayList<Point3D>();
	  Points = new ArrayList<Point>();
	  GenerateCoordinate();
 	}

	public Texture[] LoadTextures() {
	      Texture[] iTextures = new Texture[7];
		  // Загрузка изображений текстур из файлов
	      try {	  
	         // Use URL so that can read from JAR and disk file. Filename relative to project root
	         BufferedImage image1 = 
	               ImageIO.read(getClass().getClassLoader().getResource("images/red.jpg"));

	         BufferedImage image2 = 
		           ImageIO.read(getClass().getClassLoader().getResource("images/blue.jpg"));

	         BufferedImage image3 = 
			           ImageIO.read(getClass().getClassLoader().getResource("images/green.jpg"));

	         BufferedImage image4 = 
		               ImageIO.read(getClass().getClassLoader().getResource("images/crimson.jpg"));

		     BufferedImage image5 = 
		               ImageIO.read(getClass().getClassLoader().getResource("images/white.jpg"));

		     BufferedImage image6 = 
			           ImageIO.read(getClass().getClassLoader().getResource("images/yellow.jpg"));
	         
		     BufferedImage image7 = 
			           ImageIO.read(getClass().getClassLoader().getResource("images/black.jpg"));

		     // Create a OpenGL Texture object
	         iTextures[0] = AWTTextureIO.newTexture(GLProfile.getDefault(), image1, false); 
	         iTextures[1] = AWTTextureIO.newTexture(GLProfile.getDefault(), image2, false);
	         iTextures[2] = AWTTextureIO.newTexture(GLProfile.getDefault(), image3, false);
	         iTextures[3] = AWTTextureIO.newTexture(GLProfile.getDefault(), image4, false); 
	         iTextures[4] = AWTTextureIO.newTexture(GLProfile.getDefault(), image5, false);
	         iTextures[5] = AWTTextureIO.newTexture(GLProfile.getDefault(), image6, false);
	         iTextures[6] = AWTTextureIO.newTexture(GLProfile.getDefault(), image7, false);

	         TextureCoords textureCoords;
	         textureCoords = iTextures[0].getImageTexCoords();
	         MainTextureCoords = new float[12];
	         MainTextureCoords[0] = textureCoords.top();
	         MainTextureCoords[1] = textureCoords.bottom();
	         MainTextureCoords[2] = textureCoords.left();
	         MainTextureCoords[3] = textureCoords.right();

	      } catch (GLException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
		return iTextures;
	}
    
	@Override
	public void draw(int Index) {
		DrawBlock(gl, PlaneColorMask, Index);
	}
	
	public void Colorer(int a)
	{
		switch (a) {
		case 1:
         		Textures[0].enable(gl);
         		Textures[0].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[0].getTextureObject());
         		break;
		case 2:
         		Textures[1].enable(gl);
         		Textures[1].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[1].getTextureObject());
         		break;
		case 3:
         		Textures[2].enable(gl);
         		Textures[2].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[2].getTextureObject());
         		break;
		case 4:
         		Textures[3].enable(gl);
         		Textures[3].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[3].getTextureObject());
         		break;
		case 5:
         		Textures[4].enable(gl);
         		Textures[4].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[4].getTextureObject());
         		break;
		case 6:
         		Textures[5].enable(gl);
         		Textures[5].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[5].getTextureObject());
         		break;
     	default:
         		Textures[6].enable(gl);
         		Textures[6].bind(gl);
         		gl.glBindTexture(GL2.GL_TEXTURE_2D,Textures[6].getTextureObject());
         		break;
		}
	}
	
	public void DrawBlock(GL2 gl,
						  int[] plane,
						  int Index)
	{
	    int j = 0;
	    //gl.glRotatef(180, 1f, 0f, 0f);
	    //gl.glRotatef(180, 0f, 1f, 0f);
	    //gl.glRotatef(0, 0f, 0f, 1f);

		for (int i = 0;i < Vectors3D.size(); i++){
	    
	    	//System.out.print((i % 6) + "\t" + plane[1]+ "\t" + plane[2]+ "\t" + plane[3]+  "\n");
			switch (i % 6) {
         	case 0:
         		Colorer(plane[3]);
         		break;
         	case 1:
         		Colorer(plane[2]);
         		break;
         	case 2:
         		Colorer(plane[1]);
         		break;
         	case 3:
         		Colorer(plane[3]);
         		break;
         	case 4:
         		Colorer(plane[2]);
         		break;
         	case 5:
         		Colorer(plane[1]);
         		break;
         	}
	    	Index++;
	    	gl.glPushName(Index);
         	gl.glBegin(GL2.GL_POLYGON);
         	do{
	    		gl.glNormal3f(Vectors3D.get(i).x(), Vectors3D.get(i).y(), Vectors3D.get(i).z());
	    		gl.glTexCoord2f(Points.get(j).X,Points.get(j).Y);
	    		gl.glVertex3f(Points3D.get(j).x(), Points3D.get(j).y(), Points3D.get(j).z());
	    		j++;
	    	} while ((j == 0) || (!((j % 5) == 0)) && (j < 29));
         	gl.glEnd();
         	gl.glPopName();
	     }
	}

	public int ROR3(int Inp)
	{
		return(((Inp & 1) == 0)?(((Inp >> 1 ) ^ 2) & 3):((Inp >> 1) & 3));
	}
	
	public int ROL3(int Inp)
	{
		return(((Inp & 2) == 0)?(((Inp << 1 ) ^ 1) & 3):((Inp << 1) & 3));
	}
	
	public void GenerateCoordinate()
	{
		int a;
		int k = 0;
		float c = 0;
		float b[] = new float[3];
 		
		for (int i = 0; i < 6; i++) {
			a = ((k < 16)?1:2);
			for (int j = 0; j < 5; j++) {
				c = (float)Math.pow(-1,i);
				b[0] = ((i==2)||(i==5)?c:0);
				b[1] = ((i==1)||(i==4)?c:0);
				b[2] = ((i==0)||(i==3)?c:0);
				a = ((k < 16)?ROR3(a):ROL3(a));
				Points3D.add(new Point3D( 
						                  ((b[0]!=0)?b[0]:((a & 2) == 2)?1:-1),  
						                  ((b[1]!=0)?b[1]:(b[0]!=0)?(((a & 2) == 2)?1:-1):(((a & 1) == 1)?1:-1)),
						                  ((b[2]!=0)?b[2]:(((a & 1) == 1)?1:-1))
						                 ));
				/*
				System.out.println( 
			            ((b[0]!=0)?b[0]:((a & 2) == 2)?1:0) + "\t" +
			                ((b[1]!=0)?b[1]:(b[0]!=0)?(((a & 2) == 2)?1:0):(((a & 1) == 1)?1:0)) + "\t" + 
			            ((b[2]!=0)?b[2]:(((a & 1) == 1)?1:0)) + "\t" + a
			          ); 
				*/
				Points.add(new Point(MainTextureCoords[2], MainTextureCoords[1]));
		        Points.add(new Point(MainTextureCoords[3], MainTextureCoords[1]));
			    Points.add(new Point(MainTextureCoords[3], MainTextureCoords[0]));
			    Points.add(new Point(MainTextureCoords[2], MainTextureCoords[0]));
				Points.add(new Point(MainTextureCoords[2], MainTextureCoords[1]));
			    k++;
			}
			Vectors3D.add(new Vector3D( b[0], b[1], b[2]));
			/*
			System.out.println(b[0] + "\t" + b[1] + "\t" + b[2]);
			System.out.println("");
			*/
		}
	}
	
	public void SetPlaneColorMask(int[] PCM)
	{
		PlaneColorMask = PCM;
	}
}
