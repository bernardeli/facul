// Ricardo Bernardelli - 405.2941-1
// Sistemas distribuidos
// Feito em Mac OS X - gcc 4.2.1
//
// $ gcc -o openmp openmp.c -fopenmp

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <time.h>
#include <omp.h>

#define ARRAY_SIZE 1000
#define THREADS_SIZE 10

int main(){
    srand(time(NULL));

    int maxValue = 0;
    int maxLocal = 0;
    int size = rand() % ARRAY_SIZE; // numero maximo do array 
    int thread_size = rand() % THREADS_SIZE; // numero de threads
    int array[ARRAY_SIZE];
    int i;

    // Cria array com valores randomicos
    for (i = 0; i < size; i++) {
        array[i] = rand() % 100;
        printf("%d ", array[i]);
    }

    omp_set_num_threads(thread_size);
    #pragma omp parallel shared(maxValue) private(maxLocal)
    {
        maxLocal = 0;
        #pragma omp for schedule(static, thread_size)
        for(i = 0; i < size; i++)
            if (array[i] > maxLocal)
                maxLocal = array[i];
        
        if (maxLocal > maxValue)
            maxValue = maxLocal;

        printf("Maior na thread local: %d\n", maxLocal);
    }
    printf("\nMaior valor total: %d\n\n", maxValue);

    return 1;
}
