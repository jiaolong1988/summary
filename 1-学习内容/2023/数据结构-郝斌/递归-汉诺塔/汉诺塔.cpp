# include <stdio.h>

//n个盘子 从A 借助B  移动到C
void hannuota(int n, char A, char B, char C){
/*
如果n=4则递归算法如下：
	如果是1个盘子
		直接将A柱子上的盘子从A移到C
	否则
		先将A柱子上的[n-1 = 3]个盘子借助C移到B
		直接将A柱子上的盘子从A移到C
		最后将B柱子上的[n-1 = 3]个盘子借助A移到C		
*/
	if (1 == n){
		printf("将编号为%d的盘子直接从%c柱子移到%c柱子\n", n, A, C);
	}
	else{
		hannuota(n-1, A, C, B);
		printf("将编号为%d的盘子直接从%c柱子移到%c柱子\n", n, A, C);
		hannuota(n-1, B, A, C);
	}
}

int main(void){
	hannuota(4, 'A', 'B', 'C');
	return 0;
}