package com.dailycodeBuffer.orderSrvc.service;

public class Test {


    static int firstuni(int arr[], int n)
    {
        for (int i=0 ;i <n; i++)
        {

            int j;
            for (j=0; j<n; j++)
                if( i !=j && arr[i] ==arr[j])
                    break;

        }
        return -1;

    }
    public static void main (String [] args)
    {
        int arr[] = {1,2,3,4,2,1};
        int n = arr.length;
        System.out.print(firstuni(arr,n));


    }
}


