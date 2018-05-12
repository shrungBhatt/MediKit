package com.projects.shrungbhatt.medikit.util;

/**
 * Created by jigsaw on 12/3/18.
 */

public class URLGenerator {

    private static final String BASE_URL = "http://ersnexus.esy.es";


    public static final String FETCH_DISEASES_LIST = BASE_URL + "/fetch_diseases_query.php";

    public static final String FETCH_MEDICINES_LIST = BASE_URL + "/fetch_medicine_detail.php";

    public static final String FETCH_HOSPITALS_LIST = BASE_URL + "/fetch_hospitals.php";

    public static final String FETCH_SPECIALITY_IMAGE = BASE_URL + "/fetch_speciality_image.php";

    public static final String FETCH_DOCTOR_DETAILS = BASE_URL + "/fetch_doctor_details.php";

    public static final String ADD_APPOINTMENT_DETAILS = BASE_URL + "/add_appointment.php";

    public static final String FETCH_APPOINTMENTS = BASE_URL + "/fetch_appointments.php";

    public static final String UPDATE_APPOINTMENTSTATUS = BASE_URL + "/update_appointment_status.php";

    public static final String REGISTER_USER = BASE_URL + "/medikit_register.php";

    public static final String USER_LOGIN = BASE_URL + "/medikit_login.php";

    public static final String FETCH_DOCTOR_APPOINTMENTS = BASE_URL + "/fetch_doctor_appointments.php";

    public static final String FETCH_DOCTOR_USER_PROFILE = BASE_URL + "/fetch_doctor_user_profile.php";

    public static final String UPDATE_DOCTOR_USER_PROFILE = BASE_URL + "/medikit_update_doctor_profile.php";
}
