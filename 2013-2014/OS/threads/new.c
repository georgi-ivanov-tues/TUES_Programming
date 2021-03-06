#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

#define NUM_THREADS 5

/* Obqsneniq:
	1. Adresa na dadena promenliva vinagi e adresa na parviq byte
	
	2. Adresite na 64-bitovata sistema sa 8 bytovi (bez zna4enie dali sa int*, char*, ...)
	
	3.
	int a;
	&a; // poli4avame pointer ot tipa na tazi promenliva (int*)

	char* b;
	&b; // char**

	int c; // Zadelqme 4 byte-a
	int* p = &c; // Zadelqme 8 byte s adresa na c
	*p = 1; // Procesora se pozicionira v adresa na c i v sledva6tite 4 kletki pi6e 1
	printf("%d\n", c); // Printira 1
	
	4. Pointer == adress (v C)
	Vseki edin pointer moje da bude prevarnat v drug tip pointer
	
	5. void* - tip pointer
	*data; // ne moje, kompilatora ne dava
	void *data; // kompilatora dava, vupkrei 4e e su6toto
	
	6. Main funckiqta sushto e nishka!
*/



void *print_hello(void *data){	
	//size_t thread_num = *(size_t*)data; // Castvame(smenqme tipa) data da stane size_t* i vlizame kadeto so4i pointera, ot tam 4etem 8 byte-a (ako nqma (size_t*) data e void* i kompilatora ne ni dava)
	
	size_t thread_num = (size_t)data; // Ot adres data vzemi chisloto i go napravi size_t
	printf("Hello from thread: %zd\n", thread_num); // %zd kogato ne znae kolko golqma e promenlivata
	
	pthread_exit(NULL); // Vrushtame nqkakva stoinost
}

int main(){
	pthread_t threads[NUM_THREADS];
	size_t i; // vid integer koito e tolkova golqm kolkoto edin pointer(mnogo e golqm)
	
	for(i = 0; i < NUM_THREADS; i++){ // Vsichki nishki polzvat edin i sasht adres!
		printf("Creating thread: %zd\n", i);
		pthread_create(&threads[i], NULL, print_hello, (void*)i/*tova podavame na funkciqta*/); // Vzimame stoinostta na i
	}

	for(i = 0; i < NUM_THREADS; i++){
		size_t status = 0; // Tova koeto podadem na pthread_exit se zapisva v status
		printf("Waiting for thread: %zd\n", i);
		pthread_join(threads[i], (void**)&status); // Izchakvame nishkite sled kato sme gi pusnali
	}

	pthread_exit(NULL); // Izlizame ot nishkata a ne ot main-a
						// Ako ima nishki main sedi i chaka da prikluchat ako nqma izliza ot processa
	
	return 0; // Nikoga ne se izpalnqva
}

// Segmentation fault (core dumped)

// Na popravka sme...
