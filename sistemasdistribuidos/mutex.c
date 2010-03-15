// Ricardo Bernardelli - 405.2941-1
//
// $ gcc mutex.c -o mutex -lpthread 
// $ ./mutex quantidade_desejada
//
// 10/03/2010 @ 00:11

#include <pthread.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>

#define THREAD_SIZE 10 // qtdade de threads pré definida em 10.

int biggest = 0;
int *values;
pthread_mutex_t mutex;

void *finder(void *args) {
    int i;
    int biggest_local = 0;
    int *a = (int *)args;
    int first = *a;
    a++;
    int last = *a;
    for(i = first; i < last; i++)
        if(biggest_local < values[i])
            biggest_local = values[i];

    pthread_mutex_lock(&mutex); // lock mutex
    if(biggest < biggest_local)
        biggest = biggest_local;
    pthread_mutex_unlock(&mutex); // unlock mutex

    return NULL;
}

int main(int argc, char *argv[]) {
    argv++;
    int size = atoi(*argv);
    if(size < THREAD_SIZE) 
        printf("Quantidade de numeros precisa ser maior ou igual ao numero de threads.\n");
    else {
        pthread_t *thread = (pthread_t *)malloc(sizeof(pthread_t)*THREAD_SIZE);
        pthread_mutex_init(&mutex, NULL);
        values = (int *)malloc(sizeof(int)*size);
        int splitted_value = size / THREAD_SIZE;
        int i;
        int n_first = 0;
        int n_last = splitted_value;
        int *n;

        srand(time(NULL));

        printf("%d valores gerados: \n{ ", size);
        for(i = 0; i < size; i++) {
            values[i] = rand() % size; // gera os numeros aleatorios ateh o valor size
            printf("%d ", values[i]);
        }
        printf("}\n");
        for(i = 0; i < THREAD_SIZE; i++) {
            n = (int *)malloc(sizeof(int)*2);
            n[0] = n_first;
            n[1] = n_last;
            pthread_create(&(thread[i]), NULL, finder, (void *)n);
            n_first = n_last;
            n_last += splitted_value;
        }
        for(i = 0; i < THREAD_SIZE; i++) {
            pthread_join(thread[i], NULL);
        }

        printf("Maior valor é: %d\n", biggest);
    }
    return 0;
}
