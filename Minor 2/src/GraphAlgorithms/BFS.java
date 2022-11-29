package GraphAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class BFS {

    public static boolean searchPath(int maze[][], int x, int y, List<Integer> path){


        if(maze[x][y]==9){
            path.add(x);
            path.add(y);
            return true;
        }

        Queue<Integer> qx=new LinkedList<>();
        Queue<Integer> qy=new LinkedList<>();

        int dx[]={1, 0, -1, 0};
        int dy[]={0, 1, 0, -1};

        HashMap<List<Integer>, List<Integer>> hm=new HashMap<>();

        qx.offer(x);
        qy.offer(y);
        hm.put(xyList(x,y), xyList(-1,-1));

        while(!qx.isEmpty() && !qy.isEmpty()){

            int tempx=qx.poll();
            int tempy=qy.poll();

            if(maze[tempx][tempy]==9){//if target
                calcPath(xyList(tempx,tempy), hm, path);
                return true;
            }
            else if(maze[tempx][tempy]==0 || maze[tempx][tempy]==8){//if white space
                maze[tempx][tempy]=2;

                for(int i=0;i<4;i++){
                    int newx=tempx+dx[i];
                    int newy=tempy+dy[i];
                    if(newx>=0 && newx<maze.length && newy>=0 && newy<maze[0].length ){
                        qx.offer(newx);
                        qy.offer(newy);

                        if(!hm.containsKey(xyList(newx, newy)))
                            hm.put( xyList(newx, newy), xyList(tempx,tempy));

                    }
                }

            }


        }

        return false;
    }

    public static List<Integer> xyList(int a,int b){
        List<Integer> list=new LinkedList();
        list.add(a);
        list.add(b);
        return list;
    }

    public static void calcPath(List<Integer> xy, HashMap<List<Integer>, List<Integer>> hm, List<Integer> path){
        if(xy.get(0)==-1 && xy.get(1)==-1){
            return;
        }
        path.add(xy.get(0));
        path.add(xy.get(1));

        List<Integer> temp=hm.get( xy);

        calcPath(temp, hm, path);
    }


    public static void main(String args[]){

        int maze[][]={
                {0, 0, 1},
                {0, 1, 9},
                {0, 0, 0}
        };
        List<Integer> path=new ArrayList<>();

        boolean reach=BFS.searchPath(maze, 0, 0, path);

        System.out.println(reach);

        for(int i=0;i<path.size();i++){
            if(i%2==0)System.out.print(path.get(i)+" ");
            else System.out.println(path.get(i));
        }
    }
}
