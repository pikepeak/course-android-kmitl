package kmitl.lab04.khunach58070011.simplemydot.model;

import java.util.ArrayList;

public class SetDot {
        private ArrayList<Dot> set;
        private Dot dot;

        public interface onDotChangedListener{
            void onDotChanged(SetDot setDot);
        }
        private onDotChangedListener listener;

        public ArrayList<Dot> getSet() {
            return set;
        }

        public void setSet(Dot dot) {
            this.set.add(dot);
            this.listener.onDotChanged(this);
        }

        public Dot getDot() {
            return dot;
        }

        public void setDot(Dot dot) {
            this.dot = dot;
        }

        public SetDot(onDotChangedListener listener) {
            this.set = new ArrayList();
            this.listener = listener;
        }
        public void removeDot(onDotChangedListener listener){
            this.set = new ArrayList();
            this.listener.onDotChanged(this);
        }
        public boolean removesomedot(onDotChangedListener listener, int x, int y){
            boolean check = true;
            if(this.set != null){
                for (int i = 0; i < set.size(); i++){
                    if (Math.abs((x - set.get(i).getCenterX())) <= set.get(i).getRadius() && Math.abs(y - set.get(i).getCenterY()) <= set.get(i).getRadius()){
                        set.remove(i);
                        this.listener.onDotChanged(this);
                        return check = false;
                    }
                }
            }
            return check;
        }
}
