# crud1

Это консольное CRUD приложение, имеющее сущности 
Skill,
Account,
Developer,
и позволяет с ними работать.

Приложение имплементирует интерфейс GenericRepository, который содержит следующие методы: 
    T getById(ID id); //получить элемент по ИД

    List<T> getAll(); //получить ВСЕ элементы

    void create(T t);//создать новый элемент

    void update(T t);//обновить элемент

    void deleteById(ID id);//удалить элемент по ИД
