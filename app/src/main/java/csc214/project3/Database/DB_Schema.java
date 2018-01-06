package csc214.project3.Database;


public class DB_Schema {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "v5.db"; // database name

    public static final class CityTable{

        public static final String TABLE_NAME = "City";

        public static final class Columns { // name of each attribute
            public static final String CITY_NAME = "CityName"; // primary key
            public static final String CITY_IMAGE = "CityImage";
        }
    }

    public static final class AttractionTable{

        public static final String TABLE_NAME = "Attraction";

        public static final class Columns { // name of each attribute
            public static final String ATTRACTION_NAME = "AttractionName"; // primary key
            public static final String PREVIEW_IMAGE = "PreviewImage";
            public static final String INFO = "Info";
            public static final String CITY = "City";
            public static final String IMAGE01 = "Image01";
            public static final String IMAGE02 = "Image02";
            public static final String IMAGE03 = "Image03";
        }
    }

    public static final class AccountTable{

        public static final String TABLE_NAME = "Account";

        public static final class Columns { // name of each attribute
            public static final String USERNAME = "UserName"; // primary key
            public static final String PASSWORD = "Password";
        }

    }

    public static final class ProfileTable{

        public static final String TABLE_NAME = "Profile"; // table name

        public static final class Columns{ // name of each attribute

            public static final String USERNAME = "UserName"; // primary key
            public static final String NAME = "Name";
            public static final String HOMETOWN = "Hometown";
            public static final String PATH_PHOTO = "PathToPhoto";
            public static final String BIO = "Biography";
        }
    }

    public static final class FavoriteTable {

        public static final String TABLE_NAME = "Favorite";

        public static final class Columns{
            public static final String USERNAME = "UserName";

            public static final String FAVORITE = "FavoritePlace";
        }
    }

}
