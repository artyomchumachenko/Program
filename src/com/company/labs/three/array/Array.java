package com.company.labs.three.array;

interface Array<T> extends Iterable<T> {

    /**
     * Возвращает количество элементов в этом списке.
     * Если этот список содержит больше элементов, чем Integer.MAX_VALUE, то возвращает Integer.MAX_VALUE.
     *
     * @return количество элементов в этом списке
     */
    int size();

    /**
     * Возвращает true, если этот список не содержит элементов.
     *
     * @return true, если этот список не содержит элементов, иначе false
     */
    boolean isEmpty();

    /**
     * Возвращает true, если этот список содержит указанный элемент.
     * Более формально, возвращает true тогда и только тогда, когда этот список содержит
     * хотя бы один элемент e, такой что Objects.equals (o, e).
     *
     * @param o - элемент, наличие которого в этом списке необходимо проверить
     * @return true, если этот список содержит указанный элемент
     */
    boolean contains(T o);

    /**
     * Возвращает массив, содержащий все элементы в этом списке в правильной последовательности
     * (от первого до последнего элемента).
     * Возвращенный массив будет «безопасным» в том смысле, что в этом списке не будет никаких ссылок на него.
     * (Другими словами, этот метод должен выделить новый массив, даже если этот список поддерживается массивом).
     * Таким образом, вызывающий может изменить возвращаемый массив.
     * Этот метод действует как мост между API на основе массива и на основе коллекции.
     *
     * @return массив, содержащий все элементы в этом списке в правильной последовательности
     */
    T[] toArray();

    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param o - элемент, который будет добавлен к этому списку
     * @return истина (как указано в Collection.add)
     */
    boolean add(T o);

    /**
     * Добавляет все элементы в указанной коллекции в конец этого списка в том порядке.
     *
     * @param c коллекция, содержащая элементы, которые будут добавлены в этот список
     * @return истина, если этот список изменился в результате вызова
     */
    boolean addAll(Array<T> c);

    /**
     * Вставляет все элементы указанной коллекции в этот список в указанную позицию.
     * Сдвигает элемент, который в данный момент находится в этой позиции (если есть), и любые последующие
     * элементы вправо (увеличивает их индексы). Новые элементы появятся в этом списке в том порядке,
     * в котором они возвращаются итератором указанной коллекции.
     *
     * @param index - индекс, в который нужно вставить первый элемент из указанной коллекции
     * @param c     коллекция, содержащая элементы, которые будут добавлены в этот список
     * @return истина, если этот список изменился в результате вызова
     */
    boolean addAll(int index, Array<T> c);

    /**
     * Удаляет все элементы из этого списка.
     * Список будет пустым после того, как этот вызов вернется.
     */
    void clear();

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     *
     * @param index - индекс возвращаемого элемента
     * @return элемент в указанной позиции в этом списке
     */
    T get(int index);

    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     *
     * @param index   - индекс заменяемого элемента
     * @param element - элемент, который будет сохранен в указанной позиции
     * @return элемент ранее в указанной позиции
     */
    T set(int index, T element);

    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Сдвигает элемент, который в данный момент находится в этой позиции (если есть),
     * и любые последующие элементы вправо (добавляет единицу к их индексам).
     *
     * @param index   - индекс, по которому должен быть вставлен указанный элемент
     * @param element - элемент для вставки
     */
    void add(int index, T element);

    /**
     * Удаляет элемент в указанной позиции в этом списке .
     * Сдвигает любые последующие элементы влево (вычитает единицу из их индексов).
     * Возвращает элемент, который был удален из списка.
     *
     * @param index - индекс удаляемого элемента
     */
    void remove(int index);

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     * Если в этом списке нет элемента, он остается неизменным.
     * Более формально удаляет элемент с наименьшим индексом i, так что Objects.equals (o, get (i))
     * (если такой элемент существует). Возвращает истину, если этот список содержит указанный элемент
     * (или, что эквивалентно, если этот список изменился в результате вызова).
     *
     * @param o - элемент, который нужно удалить из этого списка, если есть
     * @return истина, если этот список содержал указанный элемент
     */
    boolean remove(Object o);

    /**
     * Возвращает индекс первого вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент.
     * Более формально, возвращает наименьший индекс i, такой что Objects.equals (o, get (i)), или -1,
     * если такого индекса нет.
     *
     * @param o - элемент для поиска
     * @return индекс первого вхождения указанного элемента в этом списке или -1, если этот список не содержит элемент
     */
    int indexOf(T o);

    /**
     * Возвращает индекс последнего вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент.
     * Более формально возвращает наивысший индекс i,
     * такой что Objects.equals (o, get (i)), или -1, если такого индекса нет.
     *
     * @param o - элемент для поиска
     * @return индекс последнего вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент
     */
    int lastIndexOf(T o);

    /**
     * Возвращает представление части этого списка между указанным fromIndex, включительно, и toIndex, исключая.
     * (Если fromIndex и toIndex равны, возвращаемый список пуст.)
     *
     * @param fromIndex нижняя конечная точка (включительно) подсписка
     * @param toIndex   высокая конечная точка (исключая) подсписка
     * @return вид указанного диапазона в этом списке
     */
    Array<T> subList(int fromIndex, int toIndex);

    /**
     * Удаляет из этого списка все его элементы, содержащиеся в указанной коллекции.
     *
     * @param c - коллекция, содержащая элементы, которые нужно удалить из этого списка
     * @return истина, если этот список изменился в результате вызова
     */
    boolean removeAll(Array<T> c);

    /**
     * Возвращает true, если этот список содержит все элементы указанной коллекции.
     *
     * @param c - коллекция, подлежащая проверке на наличие в этом списке
     * @return истина, если этот список содержит все элементы указанной коллекции
     */
    boolean containsAll(Array<T> c);
}