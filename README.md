dotcompanyerp
=============

Alteração Importante.

Descrição: Acréscimo da camada BUSINESS. Esta camada ficará responsável por centralizar todas as regras de negócio de determinado módulo como também pelo desacomplamento das regras de negócio com a camada de controle (CONTROLLER). O seu funcionamento fica independente do tipo de controle que poderá ser implementado (VAADIN, JSF, STRUTS, ...). 
A camada de controle (CONTROLLER) passa a ficar responsável pela ligação da visão (VIEW) com o mesmo (CONTROLLER) como também pela manipulação da iteração visual. A camada DAO passa a ficar responsável pela comunicação, pela iteração e pela manipulação com o banco de dados apenas. As camadas CONTROLLER e DAO não devem conter regras de negócio implementado nelas, apenas suas funções respectivamente.

