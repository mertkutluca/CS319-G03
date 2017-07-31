package Entity_Objects;

public class Door extends MapObject{
        public boolean enabled = false;
	public Door(double X, double Y, String I) {
		super(X, Y,I);
		// TODO Auto-generated constructor stub
	}
        public void setEnabled(){
            this.enabled = true;
        }
        public boolean getEnabled(){
            return this.enabled;
        }
}