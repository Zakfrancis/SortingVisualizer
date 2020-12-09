package algorithms;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafxpack.MainController;


public class Sorting {
	
	private boolean flag = false;
	private ArrayList<Integer> list = new ArrayList<Integer>(5);

	public Sorting(){
		for(int i=0;i<5;i++){
			list.add((int)((Math.random()*(24)+1)+1));
		}
	}
	
	public void resize(int newSize){
		if(flag == false) {
			list.clear();
			for(int i=0;i<newSize;i++){
				list.add((int)(Math.random()*(newSize*5-1)+1)+1);
			}
		}
	}
	
	public void randomize(){
		if(!list.isEmpty() && flag == false){
			for(int i=0;i<list.size();i++){
				list.set(i, (int)(Math.random()*((list.size()*5-1)+1))+1);
			}
		}
		
	}
	
	public void allSort(MainController controller,int sortSwitch){
		flag = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        controller.updateList();
                    }
                };

                while (flag) {
                    	
                	switch (sortSwitch) {
                    	case 0:
	                    	bubbleSort(updater);
	                    	break;
                    	case 1:
                    		insertionSort(updater);
                    		break;
                    	case 2:
                    		quickSort(updater);
                	}
                	
                	flag = false;
                	controller.toggleStatusText();
                    
                }
            }

        });
        thread.setDaemon(true);
        thread.start();
	}
	
	private void bubbleSort(Runnable updater) {
		for(int i=0;i<list.size()-1;i++){
			for(int j=0;j<list.size()-1;j++){
				if(list.get(j)>list.get(j+1)){
					int temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
				Platform.runLater(updater);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void insertionSort(Runnable updater) {
		try {
	        for (int i = 1; i < list.size(); i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(list.get(j) < list.get(j-1)){
	                    int temp = list.get(j);
	                    list.set(j, list.get(j-1));
	                    list.set(j-1, temp);
	                }
	                Platform.runLater(updater);
	                Thread.sleep(5);
	            }
	        }
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	private void quickSort(Runnable updater) {
		quicksort(list,0,list.size()-1, updater);
	}
	
	private void quicksort(ArrayList<Integer> a, int low, int high,Runnable updater) 
	{
		if (a == null || a.size() == 0)
			return;
 
		if (low >= high)
			return;
 
		int middle = low + (high - low) / 2;
		int pivot = a.get(middle);
 
		int i = low, j = high;
		while (i <= j) 
		{
			while (a.get(i) < pivot) 
			{
				i++;
			}
 
			while (a.get(j) > pivot) 
			{
				j--;
			}
 
			if (i <= j) 
			{
				int temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
				i++;
				j--;
				Platform.runLater(updater);
                try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
 
		if (low < j)
			quicksort(a, low, j,updater);
 
		if (high > i)
			quicksort(a, i, high,updater);
	}
	
	
	
	
	public ArrayList<Integer> getList(){
		return list;
	}
	
	public int getMaxNum(){
		return list.size()*5;
	}
	
}
