class Skill {
    long id;
    String name;

    Skill(long id, String name){
        this.id = id;
        this.name = name;
    }
    Skill(String name){

        this.name = name;
    }

    public String toString(){

        return id + "," + name;
    }

}










/*
    public void create(String name){
        File file = new File(name);
        try {
            if (file.createNewFile()) {
                System.out.println(name + " файл создан в корневой директории проекта");
            } else System.out.println(name + " файл уже существует в корневой директории проекта");
        }catch(Exception e){}
    }
    public void read(String name){

        try (FileReader reader = new FileReader(name)){
            int c;
            while((c = reader.read()) != -1){
                System.out.print((char) c);
            }
            System.out.println();
        }catch(Exception e){}
    }
    public void update(String name, String text){
        File file = new File(name);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(name, true)) {
                writer.write(text);
            } catch (Exception e) {

            }
            System.out.println("Файл " + name + " был успешно обновлен!");
        } else {
            System.out.println("Файл не был найден");
        }
    }
    public void delete(String name){
        File file = new File(name);
        try {
            if (file.delete()) {
                System.out.println(name + " файл удалён в корневой директории проекта");
            } else System.out.println(name + " файла не было в корневой директории проекта");
        }catch(Exception e){}
    }

*/