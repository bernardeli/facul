#include "mpi.h"
#include <stdio.h>
#include <time.h>
#include <math.h>

#define SIZE 5 

int main(int argc, char **argv){

    int array[SIZE];
    int sorted_array[SIZE];

    int proc_size;
    int i;
    int count = 0;
    int id;

    MPI_Status status;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &proc_size);
    MPI_Comm_rank(MPI_COMM_WORLD, &id);

    srand(time(NULL));

    if(id == 0){
        printf("Starting array: \n");
        for(i = 0; i < SIZE; i++) {
            array[i] = rand() % (SIZE * 5);
            printf("%d ", array[i]);
        }
    }

    MPI_Bcast(array, SIZE, MPI_INT, 0, MPI_COMM_WORLD);

    for(i = 0; i < SIZE ; i++)
        if(array[i] < array[id])
            count++;

    if(id != 0)
        MPI_Send(&count, 1, MPI_INT, 0, 99, MPI_COMM_WORLD);
    else {
        sorted_array[count] = array[0];

        for(i = 1; i< proc_size; i++) {
            MPI_Recv(&count, 1, MPI_INT, i, 99, MPI_COMM_WORLD, &status);
            printf("proc %d: %d\n", i, count);
            sorted_array[count] = array[i];
        }
    }

    if(id == 0){
        printf("Sorted array: \n");
        for(i = 0; i < SIZE; i++) {
            printf("%d ", sorted_array[i]);
        }
    }

    return 1;
}
