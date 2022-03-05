/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package HW;


public class App {
    public int[] evaluate(int[] coef, int nTh, char mode)
    {
        if(coef == null || (mode != 'd' && mode != 'i') || nTh < 0)
        {
            return null;
        }

        int[] result = null;
        if(mode == 'd')
        {
            for(int i=0; i < nTh; i++)
            {
                result = new int[coef.length-1];

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
                result = new int[coef.length+1];

                for(int j=0; j < coef.length; j++)
                {
                    result[j+1] = coef[j] / (j+1); 
                }
            }
        }

        return result;
    }
}
