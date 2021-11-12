/**************************************************
*
* Nome do(a) estudante: Giovanna Rodrigues Mendes
* Trabalho 1
* Professor(a): Diego Padilha Rubert
*
*/

// Importando a biblioteca
#include <stdio.h>

/* Armazena os valores (o n�mero da capivara e a sua respectiva quantidade de ultrapassagens) */
typedef struct {
    int numero_capivaras;       // N�mero da capivara
    int ultrapassagem;      // N�mero de ultrapassagens
} capivara;     // Nome do tipo da vari�vel

/* A fun��o troca_valores foi criada para modificar tanto os valores do n�mero da capivara como tamb�m a
quantidade das ultrapassagens. Assim, para o primeiro print queremos saber qual capivara chegou primeiro.
Portanto, a cada n�mero dado, ap�s o estabelecimento do total dos animais, a fun��o � chamada para fazer
com que o n�mero da capivara designada "v� para frente e ultrapasse a outra." */

// Pegando como par�metro valores espec�ficos (o n�mero da capivara) dentro de um vetor
void troca_valores (int *valor_1, int *valor_2) {
    int auxiliar;   // Valor que vai armazenar um n�mero para ser atribu�do a outra vari�vel

    // Troca de valores
    auxiliar = *valor_1;
    *valor_1 = *valor_2;
    *valor_2 = auxiliar;
}

/* A fun��o intercalacao foi estabelecida para intercalar os valores obtidos (tanto o n�mero das capivaras
como tamb�m o total de suas ultrapassagens) e assim ordenar o vetor consoante n�s queremos (do n�mero
da capivara com maior valor de ultrapassagens at� o da com menor, sendo que caso elas tenham o mesmo
n�mero destas, a ordem ser� crescente, ou seja, se as capivaras 1, 2 e 3 tenham 0 ultrapassagens, elas
ser�o printadas na ordem 1, 2 e 3). */

/* Pegando como par�metro os valores: a primeira posi��o (zero), o valor do meio (resultante da soma do
total de capivaras com o zero divido por 2), o total de capivaras e o vetor completo (capivaras),
sendo do tipo capivara, possuindo tanto o campo numero_capivaras e o ultrapassagem */
void intercalacao(int primeiro_valor, int valor_meio, int ultimo_total, capivara *vetor) {

    /* Atribui��o do primeiro valor (zero) para a vari�vel i, do valor do meio para a j e zero para a
    n (esta vari�vel ser� respons�vel por indicar o �ndice do vetor auxiliar) */
    int i = primeiro_valor, j = valor_meio, n = 0;

    /* O vetor_aux � um vetor auxiliar que vai ajudar no momento de mudan�a da ordem das posi��es
    de determinados valores do vetor original que obtemos no in�cio (na main) */
    capivara vetor_aux[ultimo_total];

    /* Estabelecemos um while para atribuir ao vetor auxiliar os valores ou da direita ou da esquerda
    dependendo da condi��o que ser� indicada. O la�o vai ver os valores do vetor nos campos em todas as
    posi��es */
    while (i < valor_meio && j < ultimo_total) {

        /* Vamos estabelecer esta condi��o para colocar o valores das maiores ultrapassagens na frente ou
        o menor n�mero da capivara na frente se ela tiver o mesmo valor de ultrapassagens que outra. Assim,
        a ordem ser� crescente em rela��o ao n�mero da capivara se obterem o mesmo n�mero de ultrapassagens.
        A segunda condi��o � determinada por causa da modifica��o no vetor original, podendo ter uma posi��o
        de uma capivara com maior valor na frente de uma com menor, ou seja, a capivara 8 estar na frente da 7 */
        if (vetor[i].ultrapassagem > vetor[j].ultrapassagem || (vetor[i].numero_capivaras < vetor[j].numero_capivaras && vetor[i].ultrapassagem == vetor[j].ultrapassagem)) {
            vetor_aux[n] = vetor[i];
            i++;
        } else {
            vetor_aux[n] = vetor[j];
            j++;
        }
        n++;        // Vai aumentando o valor do �ndice do vetor auxiliar
    }

    /* Se ainda tiver n�meros que n�o chegaram at� a metade do vetor, esse la�o ser� executado */
    while (i < valor_meio) {
        vetor_aux[n] = vetor[i];
        i++;
        n++;
    }
    /* Se ainda tiver n�meros que n�o chegaram at� o final do vetor, esse la�o ser� executado */
    while (j < ultimo_total) {
        vetor_aux[n] = vetor[j];
        j++;
        n++;
    }

    /* Esse la�o funciona para colocar no vetor original as mudan�as obtidas (a ordena��o) no vetor auxiliar */
    for (i = primeiro_valor; i < ultimo_total; i++) {
        vetor[i] = vetor_aux[i-primeiro_valor];
    }
}

/* A fun��o recursiva merge_sort serve para ordenar o vetor nos dois campos estabelecidos (n�mero da capivara
e de ultrapassagens). Ela ir� estabelecer um meio at� o valor atribu�do ao valor1 ser menor que o total (n�mero
de capivaras) - 1, ou seja, quando o �ndice for o pen�ltimo do vetor (exemplo: se forem 5 capivaras, o �ltimo
�ndice ser� 4 e o pen�ltimo - o que a condi��o utiliza - � o 3). */

/* Os par�metros s�o o primeiro valor de um vetor (no caso de um que possuir 5 valores, o primeiro ser� o zero
para a chamada, por exemplo, do primeiro merge sort, no caso da segunda chamada, 2), o �ltimo (utilizando o mesmo
exemplo, na primeira chamada ser� o 2 e na segunda, o 5), al�m do vetor completo com todos os campos */
void merge_sort(int valor1, int total, capivara *vetor) {
    int meio;       // Declara��o da vari�vel meio

    /* Condi��o a ser atendida para que a fun��o recursiva merge_sort possa obter (n�o retornar, pois � void e
    n�o int), o vetor ordenado, sendo ele do tipo capivara e possuindo os campos numero_capivaras e ultrapassagem */
    if (valor1 < total-1) {
        meio = (total+valor1)/2;
        merge_sort(valor1, meio, vetor);
        merge_sort(meio, total, vetor);
        intercalacao(valor1, meio, total, vetor);
    }
}

/* Fun��o principal da qual vamos chamar as fun��es, obter informa��es e imprimir, na tela, os valores desejados */
int main(void) {

    /* Declara��o das vari�veis para estabelecer o total de capivaras (quantidade_capivaras) e o n�mero de
    ultrapassagens (ultrapassagens) */
    int quantidade_capivaras, ultrapassagens;

    scanf("%d", &quantidade_capivaras);         // L� quantas capivaras estar�o na maratona

    /* A partir da atribui��o do total desse animal, vamos declarar um vetor para as capivaras utilizando registro.
    O tipo estabelecido anteriormente � capivara e ser� usado para colocar os valores do vetor capivaras conforme o
    total delas (quantidade_capivaras) nos campos numero_capivaras e ultrapassagem */
    capivara capivaras[quantidade_capivaras];

    /* A partir da quantidade de capivaras, vamos estabelecer os n�meros das posi��es de cada capivara na linha de
    largada e seu respectivo n�mero de ultrapassagens, que no caso n�o come�ou a corrida, por isso todas tem zero atribu�do */
    for (int n = 0; n < quantidade_capivaras; n++) {
        capivaras[n].numero_capivaras = n+1;
        capivaras[n].ultrapassagem = 0;
    }

    /* Vamos ler qual capivara ultrapassou at� o fim do arquivo */
    while (scanf("%d", &ultrapassagens) != EOF) {

        /* Fazendo uma busca para ver o �ndice do n�mero da capivara que ultrapassou (ultrapassagens) e declarando i*/
        int i;
        for (i = 0; i < quantidade_capivaras && capivaras[i].numero_capivaras != ultrapassagens; i++);

        ultrapassagens = i;

        /* Aumenta a ultrapassagem de uma capivara */
        capivaras[i].ultrapassagem++;

        /* Troca os valores da capivara que foi respons�vel pela ultrapassagem e a anterior (a ultrapassada),
        como se ela fosse para a frente no vetor */
        troca_valores(&capivaras[ultrapassagens].ultrapassagem, &capivaras[ultrapassagens-1].ultrapassagem);
        troca_valores(&capivaras[ultrapassagens].numero_capivaras, &capivaras[ultrapassagens-1].numero_capivaras);
    }
    /* Printando a ordem do final da corrida */
    for (int p1 = 0; p1 < quantidade_capivaras; p1++) {

        /* Condi��o feita para quando for imprimir o �ltimo n�mero n�o tenha um espa�o depois */
        if (p1 == 0) {
            printf("%d", capivaras[p1].numero_capivaras);
        } else {
            printf(" %d", capivaras[p1].numero_capivaras);
        }
    }
    printf("\n");       // Pula a linha

    /* Vai chamar a fun��o respons�vel pela ordena��o do vetor capivaras e seus campos */
    merge_sort(0, quantidade_capivaras, capivaras);

    /* Printando a ordem das capivaras conforme o valor das ultrapassagens */
    for (int p2 = 0; p2 < quantidade_capivaras; p2++) {

        /* Condi��o feita para quando for imprimir o �ltimo n�mero n�o tenha um espa�o depois */
        if (p2 == 0) {
            printf("%d", capivaras[p2].numero_capivaras);
        } else {
            printf(" %d", capivaras[p2].numero_capivaras);
        }
    }
    printf("\n");       // Pula a linha

    return 0;       // Encerra o c�digo
}
