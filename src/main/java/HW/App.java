/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package HW;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {

    public static double[] evaluate(double[] coef, int nTh, char mode)
    {
        if(coef == null || (mode != 'd' && mode != 'i') || nTh < 0)
        {
            return null;
        }

        double[] result = null;
        if(mode == 'd')
        {
            for(int i=0; i < nTh; i++)
            {
                result = new double[coef.length-1];

                for(int j=1; j < coef.length; j++)
                {
                    result[j-1] = coef[j] * j;
                }
                coef = result;
            }
        }

        else if(mode == 'i')
        {
            for(int i=0; i < nTh; i++)
            {
                result = new double[coef.length+1];

                for(int j=0; j < coef.length; j++)
                {
                    result[j+1] = coef[j] / (double) (j+1); 
                }
                coef = result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Welcome to Evaluation Application");

        post("/compute", (req, res) -> 
        {
            Map<String, String> map = new HashMap<String, String>();

            try {
                String coef = req.queryParams("coef");
                String nTh = req.queryParams("nTh");
                char mode = req.queryParams("mode").charAt(0);

                ArrayList<Double> coefList = new ArrayList<>();

                Scanner sc1 = new Scanner(coef);
                sc1.useDelimiter(" ");

                while (sc1.hasNext())
                {
                    coefList.add(Double.parseDouble(sc1.next()));
                }
                sc1.close();

                int input2AsInt = Integer.parseInt(nTh);
                double[] result = App.evaluate(ArrayListToArray(coefList), input2AsInt, mode);

                if(mode == 'd')
                {
                    String intro = "derivative of " + doubleToString(ArrayListToArray(coefList)) + " = ";
                    map.put("intro", intro);
                }
                else if(mode == 'i')
                {
                    String intro = "integrate of " + doubleToString(ArrayListToArray(coefList)) + " = ";
                    map.put("intro", intro);
                }

                map.put("result", doubleToString(result));
            } 
            catch (Exception e) {}

            return new ModelAndView(map, "compute.mustache");
        }, 
            new MustacheTemplateEngine()
        );


        get
        ("/compute",
            (rq, rs) -> 
            {
                Map<String, String> map = new HashMap<String, String>();
                map.put("result", "not computed yet!");

                return new ModelAndView(map, "compute.mustache");
            },

            new MustacheTemplateEngine()
        );
    }

    static int getHerokuAssignedPort() 
    {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) 
        {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }

    static double[] ArrayListToArray(ArrayList<Double> list)
    {
        double array[] = new double[list.size()];

        for(int i=0; i<list.size(); i++)
        {
            array[i] = list.get(i);
        }

        return array;
    }

    static Double[] doubleToDouble(double [] arr)
    {
        Double array[] = new Double[arr.length];

        for(int i=0; i<arr.length; i++)
        {
            array[i] = arr[i];
        }

        return array;
    }

    static String doubleToString(double [] arr)
    {
        String result = "";
        for(int i=0; i<arr.length; i++)
        {
            String doubleStr = String.format("%.2f", arr[i]);
            if(arr[i] == 0)
            {
                continue;
            }
            if(arr[i] == 1)
            {
                if(i == 0)
                {
                    result += "1 + ";
                    continue;
                }
                else if(i == 1)
                {
                    result += "x" + " + ";
                }
                else
                {
                    result += "x^" + i + "+ ";
                }
            }
            else
            {
                if(i == 0)
                {
                    result += doubleStr + " + ";
                }
                else if(i == 1)
                {
                    result += doubleStr + "x" + " + ";
                }
                else
                {
                    result += doubleStr + "x^" + i + " + ";
                }
            }
            
        }

        return result.substring(0, result.length()-2);
    }
}
