package com.example.hospital.models;

public class ModelCallDetails {


    private Integer status;
    private String message;
    private DataDTO data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        private Integer id;
        private String patient_name;
        private String created_at;
        private String doctor_id;
        private Integer doc_id;
        private String nurse_id;
        private String analysis_id;
        private String status;
        private String case_status;
        private String age;
        private String phone;
        private String description;
        private String blood_pressure;
        private String sugar_analysis;
        private String tempreture;
        private String fluid_balance;
        private String respiratory_rate;
        private String heart_rate;
        private String measurement_note;
        private String image;
        private String medical_record_note;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public Integer getDoc_id() {
            return doc_id;
        }

        public void setDoc_id(Integer doc_id) {
            this.doc_id = doc_id;
        }

        public String getNurse_id() {
            return nurse_id;
        }

        public void setNurse_id(String nurse_id) {
            this.nurse_id = nurse_id;
        }

        public String getAnalysis_id() {
            return analysis_id;
        }

        public void setAnalysis_id(String analysis_id) {
            this.analysis_id = analysis_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCase_status() {
            return case_status;
        }

        public void setCase_status(String case_status) {
            this.case_status = case_status;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBlood_pressure() {
            return blood_pressure;
        }

        public void setBlood_pressure(String blood_pressure) {
            this.blood_pressure = blood_pressure;
        }

        public String getSugar_analysis() {
            return sugar_analysis;
        }

        public void setSugar_analysis(String sugar_analysis) {
            this.sugar_analysis = sugar_analysis;
        }

        public String getTempreture() {
            return tempreture;
        }

        public void setTempreture(String tempreture) {
            this.tempreture = tempreture;
        }

        public String getFluid_balance() {
            return fluid_balance;
        }

        public void setFluid_balance(String fluid_balance) {
            this.fluid_balance = fluid_balance;
        }

        public String getRespiratory_rate() {
            return respiratory_rate;
        }

        public void setRespiratory_rate(String respiratory_rate) {
            this.respiratory_rate = respiratory_rate;
        }

        public String getHeart_rate() {
            return heart_rate;
        }

        public void setHeart_rate(String heart_rate) {
            this.heart_rate = heart_rate;
        }

        public String getMeasurement_note() {
            return measurement_note;
        }

        public void setMeasurement_note(String measurement_note) {
            this.measurement_note = measurement_note;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMedical_record_note() {
            return medical_record_note;
        }

        public void setMedical_record_note(String medical_record_note) {
            this.medical_record_note = medical_record_note;
        }
    }
}
