package iha.frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.common.nio.Buffers;
import iha.figure.Arrow;
import iha.figure.CubeR;
import static java.awt.event.KeyEvent.*;

@SuppressWarnings("unused")

public class Scene extends GLCanvas 
implements 
GLEventListener, 
KeyListener, 
MouseListener, 
MouseMotionListener
{

private int ID = 0;
private int OldID = 0; 

private ArrayList<DrawObjects> Figures = new ArrayList<DrawObjects>(); 

private static final long serialVersionUID = 968882202253258274L;

private boolean isLightOn = false;
private boolean blendingEnabled =  false;
private int Direct[] = new int[3];
private float[] AR = new float[3];

private float A;
private float H;
private float W;
	
static final int NOTHING = 0, UPDATE = 1, SELECT = 2;
private int cmd = UPDATE;
private int mouse_x, mouse_y;
private int CurrentObject = 0;

private GLU glu = new GLU();

public Scene(){
      this.addGLEventListener(this);
      // For Handling KeyEvents
      this.addKeyListener(this);
      // For Handling MouseEvents
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.setFocusable(true);
      this.requestFocus();
      //CM = new CubeMatrix();
      
}

@Override
public void mouseDragged(MouseEvent e) {
}
@Override
public void mouseMoved(MouseEvent e) {
}
@Override
public void mouseClicked(MouseEvent e) {
}
@Override
public void mousePressed(MouseEvent e) {
     cmd = SELECT;
     mouse_x = e.getX();
     mouse_y = e.getY();
}
@Override
public void mouseReleased(MouseEvent e) {
	int a = (int)(CurrentObject / 1000);
	int b = (int)((CurrentObject - a * 1000) / 100);
	int c = (int)((CurrentObject - a * 1000 - b * 100) / 10);
	int d = CurrentObject - a * 1000 - b * 100 - c * 10;

	int oa = (int)(OldID / 1000);
	int ob = (int)((OldID - oa * 1000) / 100);
	int oc = (int)((OldID - oa * 1000 - ob * 100) / 10);
	int od = OldID - oa * 1000 - ob * 100 - oc * 10;

	int Da = a - oa;
	int Db = b - ob;
	int Dc = c - oc;
	int Dd = d - od;
	
	if ((Direct[0] == 0)&&(Direct[1] == 0)&&(Direct[2] == 0))
	{	
		Direct[0] = Direct[1] = Direct[2] = 0;
		
		//System.out.println("ID: \t" + OldID + "\t" + CurrentObject + "\t");
		//System.out.println("Dx: \t" + Da + "\t" + Db + "\t"  + Dc + "\t"  + Dd);
		
		if ((Da!=0)&&(Db==0)&&(Dc==0)){
			if ((Dd == 0)&&(d==1)){
				if (b == c) Direct[1] = (Da>0)?b:-b;
				if (c > b) Direct[1] = (Da>0)?-b:b;
				else Direct[1] = (Da>0)?-c:c;
			}

			if ((Dd == 0)&&(d==4)){
				if (b == c) Direct[1] = (Da>0)?b:-b;
				if (c > b) Direct[1] = (Da>0)?-c:c;
				else Direct[1] = (Da>0)?b:-b;
			}

			if ((Dd == 0)&&(d==5)){
				if (b == c) Direct[2] = (Da>0)?-b:b;
				if (c > b) Direct[2] = (Da>0)?-b:b;
				else Direct[2] = (Da>0)?c:-c;
			}
			if ((Dd == 0)&&(d==2)){
				if (b == c) Direct[2] = (Da>0)?b:-b;
				if (c > b) Direct[2] = (Da>0)?-c:c;
				else Direct[2] = (Da>0)?-b:b;
			}
		}
		
		if ((Da==0)&&(Db!=0)&&(Dc==0)){
			if ((Dd == 0)&&(d==6)){
				if (a == c) Direct[2] = (Db>0)?-a:a;
				if (c > a) Direct[2] = (Db>0)?c:-c;
				else Direct[2] = (Db>0)?c:-c;
			}

			if ((Dd == 0)&&(d==3)){
				if (a == c) Direct[2] = (Db>0)?a:-a;
				if (c > a) Direct[2] = (Db>0)?-c:c;
				else Direct[2] = (Db>0)?-c:c;
			}
			
			if ((Dd == 0)&&(d==1)){
				if (a == c) Direct[0] = (Db>0)?-a:a;
				if (c > a) Direct[0] = (Db>0)?a:-a;
				else Direct[0] = (Db>0)?c:-c;
			}

			if ((Dd == 0)&&(d==4)){
				if (a == c) Direct[0] = (Db>0)?-a:a;
				if (c > a) Direct[0] = (Db>0)?-c:c;
				else Direct[0] = (Db>0)?-a:a;
			}
		}
	
		if ((Da==0)&&(Db==0)&&(Dc!=0)){
			if (Dd == 0){
				if (d == 6){
					if (b > a) Direct[1] = (Dc>0)?-b:b;
					else Direct[1] = (Dc>0)?b:-b;
					if (a == b) Direct[1] = (Dc>0)?-a:a;
					//System.out.println(a + "\t" + b);
				}

				if (d == 3){
					if (b > a) Direct[1] = (Dc>0)?-b:b;
					else Direct[1] = (Dc>0)?b:-b;
					if (a == b) Direct[1] = (Dc>0)?a:-a;
					//System.out.println(a + "\t" + b);
				}
				
				if (d == 5){
					if (a == b) Direct[0] = (Dc>0)?a:-a;
					if (b > a) Direct[0] = (Dc>0)?-a:a;
					else Direct[0] = (Dc>0)?-b:b;
				}

				if (d == 2){
					if (a == b) Direct[0] = (Dc>0)?a:-a;
					if (b > a) Direct[0] = (Dc>0)?b:-b;
					else Direct[0] = (Dc>0)?a:-a;
				}
			}
		}
		
		
		//System.out.println("Pn: \t" + Direct[0] + "\t" + Direct[1] + "\t"  + Direct[2]);
		//System.out.println("");
		
		if (OldID == 0) {
			OldID = CurrentObject;
		} else {
			OldID = 0;
		}
		//System.out.println(x + "\t" + y + "\t" + CurrentObject + "\t" + "Released");
		//System.out.println(AR[0] + "\t" + AR[1] + "\t");
	}	
}
@Override
public void mouseEntered(MouseEvent e) {
}
@Override
public void mouseExited(MouseEvent e) {
}

@Override
public void keyTyped(KeyEvent e) {
}
@Override
public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
      switch (keyCode) {
         case VK_UP:   // rotation in x
            if (AR[0] < -360) AR[0] = 0; else AR[0] -= 15f;
        	break;
         case VK_DOWN: // rotation in x
        	 if (AR[0] > 360) AR[0] = 0; else AR[0] += 15f;
        	 break;
         case VK_LEFT: // rotation in y
        	 if (AR[1] < -360) AR[1] = 0; else AR[1] -= 15f;
            break;
         case VK_RIGHT:// rotation in y
        	 if (AR[1] > 360) AR[1] = 0; else AR[1] += 15f;
        	 break;
      }
	
}
@Override
public void keyReleased(KeyEvent e) {
}

@Override
public void display(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();  
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
	
    // Свет
    if (isLightOn) {
       gl.glEnable(GL2.GL_LIGHTING);
    } else {
       gl.glDisable(GL2.GL_LIGHTING);
    }

    // Контроль смешивания цветов
    if (blendingEnabled) {
       gl.glEnable(GL2.GL_BLEND);       // Включить смешивание цветов
       gl.glDisable(GL2.GL_DEPTH_TEST); // Выключить глубину тестирования
    } else {
       gl.glDisable(GL2.GL_BLEND);      // Выключить смешивание цветов
       gl.glEnable(GL2.GL_DEPTH_TEST);  // Включить глубину тестирования
    }
    
    gl.glLoadIdentity();                    // Сбросить матрицу модели-представления
         
    gl.glTranslatef(0f, 0f, -20.0f);         // Перемещение внутри экрана
    
    switch(cmd)
        {
        case UPDATE:
		  drawScene(gl);
          break;
        case SELECT:
          CurrentObject = RetrieveObjectID(gl, mouse_x, mouse_y);
 	      //System.out.println(mouse_x + "\t" + mouse_y + "\t" + CurrentObject);
          cmd = UPDATE;
          break;
        }
  
}
public void drawScene(GL2 gl) {
      ID = 0;
	  gl.glInitNames();
      
      for (DrawObjects i:Figures ) {
    	  	    	  
    	  i.SetPlane(Direct);
    	  i.SetAR(new float[]{AR[0],AR[1],AR[2]});
    	  
    	  i.draw(ID);
    	  
    	  if (i.GetActionFlag()) {Direct[0] = Direct[1] = Direct[2] = 0;}
    	  //System.out.print(ID + " ");
      }
}
@Override
public void dispose(GLAutoDrawable arg0) {

}
@Override
public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      
      gl.glClearDepth(1.0f);      
      gl.glEnable(GL2.GL_CULL_FACE);
      gl.glEnable(GL2.GL_DEPTH_TEST); 
      gl.glEnable(GL2.GL_NORMALIZE);	      
      gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); 
      
      gl.glDepthFunc(GL2.GL_LEQUAL);  
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); 
      gl.glShadeModel(GL2.GL_SMOOTH); 
      float[] lightAmbientValue = {0.5f, 0.5f, 0.5f, 1.0f};
      float[] lightDiffuseValue = {1.0f, 1.0f, 1.0f, 1.0f};
      float lightDiffusePosition[] = {0.0f, 0.0f, 2.0f, 1.0f};

      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightAmbientValue, 0);
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightDiffuseValue, 0);
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightDiffusePosition, 0);
      gl.glEnable(GL2.GL_LIGHT1); 
      gl.glDisable(GL2.GL_LIGHTING); 
      isLightOn = false;

      gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
      gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE);
      gl.glEnable(GL2.GL_BLEND);
      gl.glDisable(GL2.GL_DEPTH_TEST);
      blendingEnabled = false;

      gl.glEnable(GL2.GL_COLOR_MATERIAL);
      gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
	  AR[0] = AR[1] = 45f;	
	  //Figures.add(new Arrow(gl, 0.0f, 0.0f,  -2.0f));
	  Figures.add(new CubeR(gl, 0.0f, 0.0f, -16.0f));
      
      }
@Override
public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	  GL2 gl = drawable.getGL().getGL2();  

	  if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;
      A = aspect;
      W = width;
      H =  height;
      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // Выбрать матрицу проекции
      gl.glLoadIdentity();             // Сборосить матрицу проекции
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Включить трансформацию модели-представления
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // Сбросить все  
      
      glu.gluOrtho2D(0.0f,0.0f,0.0f,0.0f);
}
	
public int RetrieveObjectID(GL2 gl, int x, int y) {
	int objectsFound = 0;                       // Общее количество кликнутых обьектов
    int[] viewportCoords = new int[4];          // Массив для хранения экранных координат
    int buffsize = 100032;
    IntBuffer selectBuffer = Buffers.newDirectIntBuffer(buffsize);
    gl.glSelectBuffer(100032, selectBuffer);    // Регистрируем буфер для хранения выбранных обьектов
    gl.glGetIntegerv(GL2.GL_VIEWPORT, viewportCoords,0); // Получаем текущие координаты экрана
    gl.glMatrixMode(GL2.GL_PROJECTION);         // Переходим в матрицу проекции

    gl.glPushMatrix();                          // Переходим в новые экранные координаты
    	gl.glRenderMode(GL2.GL_SELECT);         // Позволяет рендерить обьекты без изменения фреймбуфера

    	gl.glLoadIdentity();                    // Сбросим матрицу проекции
    	glu.gluPickMatrix(x, viewportCoords[3] - y, 2, 2, viewportCoords, 0);
        glu.gluPerspective(45.0, A, 0.1, 100.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);      // Возвращаемся в матрицу GL_MODELVIEW

        drawScene(gl);                          // Теперь рендерим выбранную зону для выбора обьекта
        objectsFound = gl.glRenderMode(GL2.GL_RENDER); // Вернемся в режим отрисовки и получим число обьектов

        gl.glMatrixMode(GL2.GL_PROJECTION);    // Вернемся в привычную матрицу проекции
        gl.glPopMatrix();                      // Выходим из матрицы

        gl.glMatrixMode(GL2.GL_MODELVIEW);     // Вернемся в матрицу GL_MODELVIEW
    
        cmd = UPDATE;
    
    if (objectsFound > 0)
    {
        int lowestDepth = selectBuffer.get(1); 
        int selectedObject = selectBuffer.get(3);
        for(int i = 1; i < objectsFound; i++)
        {
            if(selectBuffer.get((i * 4) + 1) < lowestDepth)
            {
                lowestDepth = selectBuffer.get((i * 4) + 1);
                selectedObject = selectBuffer.get((i * 4) + 3);
            }
        }
        CurrentObject = selectedObject;	
        return selectedObject;
    }

    CurrentObject = 0;
    return 0;	

}

} 


